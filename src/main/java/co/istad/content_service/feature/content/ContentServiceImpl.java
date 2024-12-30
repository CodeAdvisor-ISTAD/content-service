package co.istad.content_service.feature.content;


import co.istad.content_service.base.BasedMessage;
import co.istad.content_service.base.BasedResponse;
import co.istad.content_service.domain.CommunityEngagement;
import co.istad.content_service.domain.Content;
import co.istad.content_service.domain.Tags;
import co.istad.content_service.feature.content.dto.ContentCreateRequest;
import co.istad.content_service.feature.content.dto.ContentCreatedEvent;
import co.istad.content_service.feature.content.dto.ContentResponse;
import co.istad.content_service.feature.content.dto.ContentUpdateRequest;
import co.istad.content_service.feature.tag.TagRepository;
import co.istad.content_service.mapper.ContentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;

    private final ContentMapper contentMapper;

    private final TagRepository tagRepository;

    private final KafkaTemplate<String, Object> kafkaTemplate;


//    @Override
//    public Page<ContentResponse> findContentByTag(String tag, int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Content> contentsPage = contentRepository.findByTagsName(tag, pageable);
//
//        return contentsPage.map(contentMapper::toContentResponse);
//    }
    //    @Override
//    public Page<ContentResponse> findBySlug(String slug, int page, int size) {
//        if (!contentRepository.existsBySlug(slug)) {
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND,
//                    "Content not found..."
//            );
//        }
//        // Create Pageable object with page and size, no sorting
//        Pageable pageable = PageRequest.of(page, size);
//
//        // Assuming you're searching by slug here as well, adjust query to filter by slug if needed
//        Page<Content> contentsPage = contentRepository.findBySlugContainingIgnoreCase(slug, pageable); // Filter by slug if needed
//
//        return contentsPage.map(contentMapper::toContentResponse);
//    }

//    @Override
//    public Page<ContentResponse> findByTitle(String title, int page, int size) {
//        // Create Pageable object with page and size, no sorting
//        Pageable pageable = PageRequest.of(page, size);
//
//        // Assuming you're searching by title here as well, adjust query to filter by title if needed
//        Page<Content> contentsPage = contentRepository.findByTitleContainingIgnoreCase(title, pageable); // Filter by title if needed
//
//        // Map Contents to ContentResponse
//        return contentsPage.map(contentMapper::toContentResponse);
//    }


    @Override
    public BasedMessage softDeleteById(String id, Jwt jwt) {
        log.info("Soft deleting content with id: {}", id);

        Content content = contentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Content not found..."
                )
        );
        if (!content.getAuthorUuid().equals(jwt.getClaimAsString("uuid"))) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You are not authorized to delete this content"
            );
        }

        content.setIsDeleted(true);
        contentRepository.save(content);

        return new BasedMessage("Content soft deleted successfully");
    }

    @Override
    public Page<ContentResponse> getAllContentByAuthorUuid(String authorUuid, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Content> contentsPage = contentRepository.findByAuthorUuidAndIsDeletedIsFalse(authorUuid, pageable);

        return contentsPage.map(contentMapper::toContentResponse);
    }

    @Override
    public BasedMessage updateContent(String id, ContentUpdateRequest contentUpdateRequest, Jwt jwt) {
        log.info("Updating content with title: {} and tags: {}", contentUpdateRequest.title(), contentUpdateRequest.tags());

        Content content = contentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Content not found..."
                )
        );

        if (!content.getAuthorUuid().equals(jwt.getClaimAsString("uuid"))) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You are not authorized to update this content"
            );
        }

        if (contentUpdateRequest.tags() != null && !contentUpdateRequest.tags().isEmpty()) {
            List<String> requestedTags = contentUpdateRequest.tags();
            List<Tags> existingTags = tagRepository.findByNameIn(requestedTags);

            List<String> missingTags = requestedTags.stream()
                    .filter(tag -> existingTags.stream().noneMatch(existingTag -> existingTag.getName().equals(tag)))
                    .toList();

            if (!missingTags.isEmpty()) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "The following tags are missing: " + String.join(", ", missingTags)
                );
            }
            content.setTags(existingTags.stream().map(Tags::getName).collect(Collectors.toList()));
        } else {
            content.setTags(content.getTags());
        }

        contentMapper.updateContentFromDto(contentUpdateRequest, content);

        contentRepository.save(content);

        return new BasedMessage("Content updated successfully");
    }


    @Override
    public ContentResponse findContentById(String id) {
        return contentRepository.findByIdAndIsDeletedIsFalseAndIsDraftIsFalse(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Content not found..."
                )
        );
    }

    @Override
    public Page<ContentResponse> searchContent(String query, String searchBy, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Content> contentsPage = switch (searchBy.toLowerCase()) {
            case "draft" -> contentRepository.findByIsDraftIsTrueAndIsDeletedIsFalse(pageable);
            case "tag" -> contentRepository.findByTagsContainingAndIsDeletedIsFalseAndIsDraftIsFalse(query, pageable);
            case "slug" ->
                    contentRepository.findBySlugContainingIgnoreCaseAndIsDeletedIsFalseAndIsDraftIsFalse(query, pageable);
            case "title" ->
                    contentRepository.findByTitleContainingIgnoreCaseAndIsDeletedIsFalseAndIsDraftIsFalse(query, pageable);
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid search type");
        };

        return contentsPage.map(contentMapper::toContentResponse);
    }

    @Override
    public Page<ContentResponse> findByAll(String tag, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Content> contentsPage = contentRepository.findByTitleContainingIgnoreCaseAndIsDeletedIsFalseAndIsDraftIsFalse(tag, pageable);

        return contentsPage.map(contentMapper::toContentResponse);
    }

    @Override
    public BasedResponse<?> createContent(ContentCreateRequest contentCreateRequest, Jwt jwt) {
        String userUuid = jwt.getClaimAsString("userUuid");
        if (userUuid == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "User not authenticated"
            );
        }

        log.info("Creating Content: {}", contentCreateRequest);


        // Fetch existing tags
        List<String> requestedTags = contentCreateRequest.tags();
        List<Tags> existingTags = tagRepository.findByNameIn(requestedTags);

        List<String> missingTags = requestedTags.stream()
                .filter(tag -> existingTags.stream().noneMatch(existingTag -> existingTag.getName().equals(tag)))
                .toList();

        if (!missingTags.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "The following tags are missing: " + String.join(", ", missingTags)
            );
        }

        if (contentRepository.existsBySlug(contentCreateRequest.slug())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Content with slug already exists"
            );
        }

        List<String> existingTagNames = existingTags.stream().map(Tags::getName).toList();

        CommunityEngagement communityEngagement = new CommunityEngagement();
        communityEngagement.setLikeCount(0L);
        communityEngagement.setCommentCount(0L);
        communityEngagement.setReportCount(0L);
        communityEngagement.setShareCount(0L);

        Content content = contentMapper.toContent(contentCreateRequest);

        content.setCommunityEngagement(communityEngagement);
        content.setTags(existingTagNames);
        content.setIsDeleted(false);

        contentRepository.save(content);


        // Produce event


        kafkaTemplate.send("content-created-events-topic", content.getId(), ContentCreatedEvent.builder()
                .id(content.getId())
                .title(content.getTitle())
                .authorUuid(userUuid)
                .slug(content.getSlug())
                .content(content.getContent())
                .thumbnail(content.getThumbnail())
                .keyword(content.getKeyword())
                .tags(content.getTags())
                .build());

        return BasedResponse.builder()
                .code(HttpStatus.CREATED.value())
                .payload("Question created successfully")
                .build();
    }

    @KafkaListener(topics = "content-created-events-topic", groupId = "content-service")
    public void consumeContentCreatedEvent(@Payload ContentCreatedEvent contentCreatedEvent) {
        log.info("Consumed content created event: {}", contentCreatedEvent);
    }

}
