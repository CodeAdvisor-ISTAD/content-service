package co.istad.content_service.mapper;

import co.istad.content_service.domain.Content;
import co.istad.content_service.domain.Tags;
import co.istad.content_service.feature.content.dto.ContentCreateRequest;
import co.istad.content_service.feature.content.dto.ContentResponse;
import co.istad.content_service.feature.content.dto.ContentUpdateRequest;
import org.mapstruct.*;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ContentMapper {

    // Map Content to ContentResponse
    @Mapping(target = "tags", source = "tags", qualifiedByName = "mapTagsToString")
    ContentResponse toContentResponse(Content content);

    @Mapping(target = "tags", source = "tags")
    Content toContent(ContentCreateRequest contentCreateRequest);

    // Custom method to map List<String> to List<Tags>
    default List<Tags> mapTags(List<String> tagNames) {
        if (tagNames == null) {
            return Collections.emptyList();
        }
        return tagNames.stream()
                .map(name -> {
                    Tags tag = new Tags();
                    tag.setName(name); // Assuming Tags has a setName method
                    return tag;
                })
                .toList();
    }

    // Custom method to map List<Tags> to List<String>
    @Named("mapTagsToString")
    default List<String> mapTagsToString(List<Tags> tags) {
        if (tags == null) {
            return Collections.emptyList();
        }
        return tags.stream()
                .map(Tags::getName)
                .toList();
    }

    @Mapping(target = "tags", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateContentFromDto(ContentUpdateRequest contentUpdateRequest, @MappingTarget Content content);

//    @Mapping(target = "id", ignore = true)
//    void updateContentFromDto(ContentUpdateRequest dto, @MappingTarget Content content);

}
