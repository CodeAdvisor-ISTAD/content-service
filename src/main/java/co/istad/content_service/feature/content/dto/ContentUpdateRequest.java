package co.istad.content_service.feature.content.dto;

import java.util.List;

public record ContentUpdateRequest(

        String title,

        String content, // or content

        String thumbnail,

        String keywords,

        String slug,

        List<String> tags,

        Boolean isDraft
) {
}
