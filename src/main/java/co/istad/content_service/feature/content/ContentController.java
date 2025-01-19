package co.istad.content_service.feature.content;


import co.istad.content_service.base.BasedMessage;
import co.istad.content_service.base.BasedResponse;
import co.istad.content_service.feature.content.dto.ContentCreateRequest;
import co.istad.content_service.feature.content.dto.ContentResponse;
import co.istad.content_service.feature.content.dto.ContentUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contents")
public class ContentController {

    private final ContentService contentService;


    @GetMapping("/slug/{slug}")
    ContentResponse findContentBySlug(@PathVariable String slug) {
        log.info("slug: {}", slug);
        return contentService.findContentBySlug(slug);
    }

    @GetMapping("/me")
    public String getContentMe(@AuthenticationPrincipal Jwt jwt,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size) {
//        Object oauth2 = authentication.getPrincipal();
//        Jwt jwt = (Jwt) oauth2;
        String userUuid = jwt.getClaimAsString("userUuid");
        log.info("userUuid: {}", userUuid);
        return contentService.getAllContentByAuthorUuid(userUuid, page, size).toString();
    }

    // get content by userAuthorUuid
    @GetMapping("/author/{authorUuid}")
    public Page<ContentResponse> getAllContentByAuthorUuid(@PathVariable String authorUuid, @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        return contentService.getAllContentByAuthorUuid(authorUuid, page, size);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    BasedMessage softDeleteById(@PathVariable String id,
                                @AuthenticationPrincipal Jwt jwt) {
//        Object oauth2 = auth.getPrincipal();
//        Jwt jwt = (Jwt) oauth2;
        return contentService.softDeleteById(id, jwt);
    }

//    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/{id}")
    BasedMessage updateContent(@PathVariable String id,
                               @Valid @RequestBody ContentUpdateRequest contentUpdateRequest
                               /*Authentication auth*/) {
//        Object oauth2 = auth.getPrincipal();
//        Jwt jwt = (Jwt) oauth2;
        Jwt jwt = null;
        return contentService.updateContent(id, contentUpdateRequest,jwt );
    }

    @GetMapping("/{id}")
    ContentResponse findContentById(@PathVariable String id) {
        return contentService.findContentById(id);
    }

    // http://localhost:8080/api/v1/articles/search?query=java&searchBy=title&page=0&size=10
    @GetMapping("/search")
    public Page<ContentResponse> searchContent(
            @RequestParam String query,
            @RequestParam String searchBy,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return contentService.searchContent(query, searchBy, page, size);
    }


//    @PreAuthorize("isAuthenticated()")
    @GetMapping("/all")
    Page<ContentResponse> findByAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return contentService.findByAll(""  , page, size);
    }



    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    BasedResponse<?> createArticle(@Valid @RequestBody ContentCreateRequest contentCreateRequest,
                                   @AuthenticationPrincipal Jwt jwt) {
//        Object oauth2 = auth.getPrincipal();
//        Jwt jwt = (Jwt) oauth2;
        return contentService.createContent(contentCreateRequest, jwt);
    }


}
