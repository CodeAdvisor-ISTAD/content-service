package co.istad.content_service.feature.content.dto;

import co.istad.content_service.domain.CommunityEngagement;

import java.util.List;

public record ContentProduceEventRequest(
        String title,
        String authorId,
        String slug,
        String content,
        String thumbnail,
        String keyword,
        List<String> tags,
        CommunityEngagement communityEngagement

) {
}
