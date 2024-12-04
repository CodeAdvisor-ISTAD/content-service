package co.istad.content_service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunityEngagement {
//    likeCount, commentCount, shareCount, reportCount, and lastUpdated
    private Long likeCount;

    private Long commentCount;

    private Long shareCount;

    private Long reportCount;

    private LocalDateTime lastUpdated;
}
