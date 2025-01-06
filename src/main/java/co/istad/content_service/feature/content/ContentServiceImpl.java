package co.istad.content_service.feature.content;


import co.istad.content_service.base.BasedMessage;
import co.istad.content_service.base.BasedResponse;
import co.istad.content_service.config.kafka.event.CommentProducer;
import co.istad.content_service.config.kafka.event.ContentCreatedEvent;
import co.istad.content_service.config.kafka.event.ContentReactedProducer;
import co.istad.content_service.domain.CommunityEngagement;
import co.istad.content_service.domain.Content;
import co.istad.content_service.domain.Tags;
import co.istad.content_service.feature.content.dto.ContentCreateRequest;
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

    @Override
    public ContentResponse findContentBySlug(String slug) {
        return contentRepository.findBySlugAndIsDeletedIsFalseAndIsDraftIsFalse(slug).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Content not found..."
                )
        );
    }

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
        communityEngagement.setLoveCount(0L);
        communityEngagement.setFireCount(0L);

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


    //comment-created-events-topic
    @KafkaListener(topics = "comment-created-events-topic", groupId = "content-service")
    public void commentCount(@Payload CommentProducer commentCreatedRequest) {
        try {
            Content content = contentRepository.findById(commentCreatedRequest.getContentId()).orElseThrow(
                    () -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Content not found..."
                    )
            );

            CommunityEngagement communityEngagement = content.getCommunityEngagement();
            communityEngagement.setCommentCount(communityEngagement.getCommentCount() + 1);
            content.setCommunityEngagement(communityEngagement);

            contentRepository.save(content);

            log.info("Comment count updated for content: {}", content.getId());
        } catch (Exception e) {
            log.error("Error updating comment count for content: {}", e.getMessage());
        }
    }

    @KafkaListener(topics = "content-reacted-events-topic", groupId = "content-service")
    public void reactionCount(@Payload ContentReactedProducer contentReactedProducer) {
        try {
            Content content = contentRepository.findById(contentReactedProducer.getContentId()).orElseThrow(
                    () -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Content not found..."
                    )
            );

            // Debug: Print the reaction type
            log.debug("Processing reaction: Old Reaction = {}, New Reaction = {}",
                    contentReactedProducer.getOldReactionType(),
                    contentReactedProducer.getReactionType());

            // Decrement old reaction count
            if (contentReactedProducer.getOldReactionType() != null) {
                switch (contentReactedProducer.getOldReactionType().toUpperCase()) {
                    case "LIKE":
                        content.getCommunityEngagement().setLikeCount(content.getCommunityEngagement().getLikeCount() - 1);
                        break;
                    case "FIRE":
                        content.getCommunityEngagement().setFireCount(content.getCommunityEngagement().getFireCount() - 1);
                        break;
                    case "LOVE":
                        content.getCommunityEngagement().setLoveCount(content.getCommunityEngagement().getLoveCount() - 1);
                        break;
                    default:
                        log.warn("Unknown old reaction type: {}", contentReactedProducer.getOldReactionType());
                        break;
                }
            }

            // Increment new reaction count
            switch (contentReactedProducer.getReactionType().toUpperCase()) {
                case "LIKE":
                    content.getCommunityEngagement().setLikeCount(content.getCommunityEngagement().getLikeCount() + 1);
                    break;
                case "DISLIKE":
                    content.getCommunityEngagement().setLikeCount(content.getCommunityEngagement().getLikeCount() - 1);
                    break;
                case "FIRE":
                    content.getCommunityEngagement().setFireCount(content.getCommunityEngagement().getFireCount() + 1);
                    break;
                case "LOVE":
                    content.getCommunityEngagement().setLoveCount(content.getCommunityEngagement().getLoveCount() + 1);
                    break;
                case "UNLOVE":
                    content.getCommunityEngagement().setLoveCount(content.getCommunityEngagement().getLoveCount() - 1);
                    break;
                case "UNFIRE":
                    content.getCommunityEngagement().setFireCount(content.getCommunityEngagement().getFireCount() - 1);
                    break;
                default:
                    log.warn("Unknown new reaction type: {}", contentReactedProducer.getReactionType());
                    break;
            }

            contentRepository.save(content);
            log.info("Reaction count updated for content: {}. Old Reaction: {}, New Reaction: {}",
                    content.getId(),
                    contentReactedProducer.getOldReactionType(),
                    contentReactedProducer.getReactionType()
            );
        } catch (Exception e) {
            log.error("Error updating reaction count for content {}: {}", contentReactedProducer.getContentId(), e.getMessage());
        }
    }

    // content-reported-events-topic
    @KafkaListener(topics = "content-reported-events-topic", groupId = "content-service")
    public void reportCount(@Payload ContentReactedProducer contentReactedProducer) {
        try {
            Content content = contentRepository.findById(contentReactedProducer.getContentId()).orElseThrow(
                    () -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Content not found..."
                    )
            );

            CommunityEngagement communityEngagement = content.getCommunityEngagement();
            communityEngagement.setReportCount(communityEngagement.getReportCount() + 1);
            content.setCommunityEngagement(communityEngagement);

            contentRepository.save(content);

            log.info("Report count updated for content: {}", content.getId());
        } catch (Exception e) {
            log.error("Error updating report count for content: {}", e.getMessage());
        }
    }

}
