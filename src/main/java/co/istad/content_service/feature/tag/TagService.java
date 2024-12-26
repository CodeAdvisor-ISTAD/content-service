package co.istad.content_service.feature.tag;

import co.istad.content_service.feature.tag.dto.TagResponse;

import java.util.List;
import java.util.Set;

public interface TagService {
    List<TagResponse> findAll(String filter);

    TagResponse findByName(String name);

    Set<TagResponse> getPopularTags();

    Set<TagResponse> getTrendingTags();
}
