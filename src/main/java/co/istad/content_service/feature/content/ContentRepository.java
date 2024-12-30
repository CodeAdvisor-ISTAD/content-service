package co.istad.content_service.feature.content;

import co.istad.content_service.domain.Content;
import co.istad.content_service.feature.content.dto.ContentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ContentRepository extends MongoRepository<Content, String> {

    Optional<ContentResponse> findByTitle(String title);

    Optional<ContentResponse> findByIdAndIsDeletedIsFalseAndIsDraftIsFalse(String id);

    Optional<Content> findByIdAndIsDeletedIsFalse(String id);

    Page<Content> findByTitleContainingIgnoreCaseAndIsDeletedIsFalseAndIsDraftIsFalse(String title, Pageable pageable);

    Boolean existsBySlug(String slug);

    Page<Content> findByTagsContainingAndIsDeletedIsFalseAndIsDraftIsFalse(String tag, Pageable pageable);

    Page<Content> findBySlugContainingIgnoreCaseAndIsDeletedIsFalseAndIsDraftIsFalse(String slug, Pageable pageable);

    Page<Content> findByIsDraftIsTrueAndIsDeletedIsFalse(Pageable pageable);

    Page<Content> findByAuthorUuidAndIsDeletedIsFalse(String authorUuid, Pageable pageable);
}
