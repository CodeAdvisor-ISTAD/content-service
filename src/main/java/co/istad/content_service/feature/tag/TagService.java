package co.istad.content_service.feature.tag;

import co.istad.content_service.feature.tag.dto.TagResponse;

import java.util.List;

public interface TagService {
    List<TagResponse> findAll(String filter);

    TagResponse findByName(String name);
}
