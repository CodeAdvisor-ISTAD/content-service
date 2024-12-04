package co.istad.content_service.feature.tag;

import co.istad.content_service.domain.Tags;
import co.istad.content_service.feature.tag.dto.TagResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
}
