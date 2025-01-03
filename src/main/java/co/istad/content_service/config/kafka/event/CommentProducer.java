package co.istad.content_service.config.kafka.event;

import co.istad.content_service.config.kafka.BaseProducer;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentProducer implements BaseProducer {
    private String userId;
    private String contentId;
    private String type;
    private String body; // Add this field to match the payload
}