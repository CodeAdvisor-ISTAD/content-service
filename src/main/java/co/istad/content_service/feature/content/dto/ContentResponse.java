package co.istad.content_service.feature.content.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ContentResponse(
        String title,

        String content, // or content

        String thumbnail,

        String keywords,

        String slug,

        List<String> tags,

        Boolean isPublished,

        String authorId,

        LocalDateTime createdDate


) {
}
