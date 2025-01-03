package co.istad.content_service.config.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContentCreatedEvent{
    private String id;
    private String title;
    private String authorUuid;
    private String slug;
    private String content;
    private String thumbnail;
    private String keyword;
    private List<String> tags;
}
