package co.istad.content_service.feature.content.dto;

import co.istad.content_service.domain.CommunityEngagement;

import java.time.LocalDateTime;
import java.util.List;

public record ContentResponse(
        String title,

        String content, // or content

        String thumbnail,

        String keywords,

        String slug,

        List<String> tags,

        CommunityEngagement communityEngagement,

        Boolean isDraft,

        String authorId,

        LocalDateTime createdDate


) {
}
