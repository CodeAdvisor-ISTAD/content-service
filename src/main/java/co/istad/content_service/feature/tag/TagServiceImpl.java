package co.istad.content_service.feature.tag;

import co.istad.content_service.domain.Tags;
import co.istad.content_service.feature.tag.dto.TagResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<TagResponse> findAll(String filter) {
        log.info("Fetching all tags with filter: {}", filter);
        if (filter == null || filter.equalsIgnoreCase("asc")) {
            return tagRepository.findAllByOrderByNameAsc();
        } else if (filter.equalsIgnoreCase("desc")) {
            return tagRepository.findAllByOrderByNameDesc();
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid filter value. Use 'asc' or 'desc'."
            );
        }
    }

    @Override
    public TagResponse findByName(String name) {
        log.info("Fetching tag by name: {}", name);
        Tags tag = tagRepository.findByName(name);
        return new TagResponse(tag.getName());
    }

    @Override
    public Set<TagResponse> getPopularTags() {
        // Assuming tagRepository is injected and TagResponse is a DTO
        List<Tags> tags = tagRepository.findAll(PageRequest.of(0, 10)).getContent(); // Retrieves first 10 tags
        Set<TagResponse> tagResponses = new HashSet<>();

        // Convert Tag entities to TagResponse DTOs
        for (Tags tag : tags) {
            tagResponses.add(new TagResponse(tag.getName()));
        }

        return tagResponses;
    }

    @Override
    public Set<TagResponse> getTrendingTags() {
        // Assuming tagRepository is injected and TagResponse is a DTO
        List<Tags> tags = tagRepository.findAll(PageRequest.of(0, 10)).getContent(); // Retrieves first 10 tags
        Set<TagResponse> tagResponses = new HashSet<>();

        // Convert Tag entities to TagResponse DTOs
        for (Tags tag : tags) {
            tagResponses.add(new TagResponse(tag.getName()));
        }

        return tagResponses;
    }
}
