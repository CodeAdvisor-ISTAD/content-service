package co.istad.content_service.feature.content;


import co.istad.content_service.base.BasedMessage;
import co.istad.content_service.feature.content.dto.ContentCreateRequest;
import co.istad.content_service.feature.content.dto.ContentResponse;
import co.istad.content_service.feature.content.dto.ContentUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contents")
public class ContentController {

    private final ContentService contentService;


//    @GetMapping("/tag/{tag}")
//    Page<ContentResponse> findContentByTag(
//            @PathVariable String tag,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//    ) {
//        return contentService.findContentByTag(tag, page, size);
//    }
    //    @GetMapping("/slug/{slug}")
//    Page<ContentResponse> findArticleBySlug(
//            @PathVariable String slug,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//    ) {
//        return contentService.findBySlug(slug, page, size);
//    }


//    @GetMapping
//    Page<ContentResponse> findContentByTitle(
//            @RequestParam String title,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//    ) {
//        return contentService.findByTitle(title, page, size);
//    }

    @GetMapping("/tag/{tag}")
    Page<ContentResponse> findContentByTags(
            @PathVariable String tag,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return contentService.getAllContentByTags(tag, page, size);
    }

    @DeleteMapping("/{id}")
    BasedMessage softDeleteById(@PathVariable String id) {
        return contentService.softDeleteById(id);
    }

    @PatchMapping("/{id}")
    BasedMessage updateContent(@PathVariable String id, @Valid @RequestBody ContentUpdateRequest contentUpdateRequest) {
        return contentService.updateContent(id, contentUpdateRequest);
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


    @GetMapping("/all")
    Page<ContentResponse> findByAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return contentService.findByAll(""  , page, size);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    BasedMessage createArticle(@Valid @RequestBody ContentCreateRequest contentCreateRequest) {
        return contentService.createContent(contentCreateRequest);
    }


}
