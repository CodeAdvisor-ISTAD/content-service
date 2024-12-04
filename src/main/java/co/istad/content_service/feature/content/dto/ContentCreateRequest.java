package co.istad.content_service.feature.content.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ContentCreateRequest(

        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Content is required")
        String content, // or content

        @NotBlank(message = "Cover URL is required")
        String thumbnail,

        @NotBlank(message = "Keywords is required")
        String keywords,

        @NotBlank(message = "Slug is required")
        String slug,

        @NotNull(message = "Tags is required")
        List<String> tags,

        @NotNull(message = "User ID is required")
        Long authorId,

        @NotNull(message = "isDraft is required")
        Boolean isDraft

) {
}
