package co.istad.content_service.feature.content;


import co.istad.content_service.base.BasedMessage;
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
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;

    private final ContentMapper contentMapper;

    private final TagRepository tagRepository;


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
    public BasedMessage softDeleteById(String id) {
        log.info("Soft deleting content with id: {}", id);

        Content content = contentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Content not found..."
                )
        );

        content.setIsDeleted(true);
        contentRepository.save(content);

        return new BasedMessage("Content soft deleted successfully");
    }

    @Override
    public BasedMessage updateContent(String id, ContentUpdateRequest contentUpdateRequest) {
        log.info("Updating content with title: {} and tags: {}", contentUpdateRequest.title(), contentUpdateRequest.tags());

        Content content = contentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Content not found..."
                )
        );

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
            content.setTags(existingTags);
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
            case "tag" -> contentRepository.findByTagsNameAndIsDeletedIsFalseAndIsDraftIsFalse(query, pageable);
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
    public BasedMessage createContent(ContentCreateRequest contentCreateRequest) {
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
        CommunityEngagement communityEngagement = new CommunityEngagement();
        communityEngagement.setLikeCount(0L);
        communityEngagement.setCommentCount(0L);
        communityEngagement.setReportCount(0L);
        communityEngagement.setShareCount(0L);
        
        Content content = contentMapper.toContent(contentCreateRequest);
        
        content.setTags(existingTags);

        contentRepository.save(content);

        return new BasedMessage("Content created successfully");
    }

}
