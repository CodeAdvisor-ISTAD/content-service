package co.istad.content_service.feature.content;

import co.istad.content_service.base.BasedMessage;
import co.istad.content_service.base.BasedResponse;
import co.istad.content_service.feature.content.dto.ContentCreateRequest;
import co.istad.content_service.feature.content.dto.ContentResponse;
import co.istad.content_service.feature.content.dto.ContentUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.security.oauth2.jwt.Jwt;

public interface ContentService {
    BasedResponse<?> createContent(ContentCreateRequest contentCreateRequest, Jwt jwt);

    Page<ContentResponse> findByAll(String tag, int page, int size);

    Page<ContentResponse> searchContent(String query, String searchBy, int page, int size);

    ContentResponse findContentById(String id);

    BasedMessage updateContent(String id, ContentUpdateRequest contentUpdateRequest, Jwt jwt);

    BasedMessage softDeleteById(String id, Jwt jwt);

    Page<ContentResponse> getAllContentByAuthorUuid(String authorUuid, int page, int size);



//    Page<ContentResponse> findBySlug(String slug, int page, int size);

//    Page<ContentResponse> findByTitle(String title, int page, int size);
//    Page<ContentResponse> findContentByTag(String tag, int page, int size);


}

