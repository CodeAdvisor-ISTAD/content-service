package co.istad.content_service.feature.tag;

import co.istad.content_service.feature.tag.dto.TagResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
