package co.istad.content_service.feature.tag;

import co.istad.content_service.feature.tag.dto.TagResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tags")
public class TagController {
    private final TagService tagService;


    @GetMapping("/all") // http://localhost:port/api/v1/tags/all?filter=desc
    public List<TagResponse> findAll(@RequestParam(defaultValue = "asc") String filter) {
        return tagService.findAll(filter);
    }

    @GetMapping("/{name}") // http://localhost:port/api/v1/tags/name?name=java
    public TagResponse findByName(@PathVariable String name) {
        return tagService.findByName(name);
    }

    @GetMapping("/popular") // http://localhost:port/api/v1/tags/limit10
    public Set<TagResponse> get10Tags() {
        return tagService.getPopularTags();
    }


    @GetMapping("/trending") // http://localhost:8082/api/v1/tags/trending
    public Set<TagResponse> getTrendingTags() {
        return tagService.getTrendingTags();
    }
}
