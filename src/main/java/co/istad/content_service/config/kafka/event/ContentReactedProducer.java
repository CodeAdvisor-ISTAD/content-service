package co.istad.content_service.config.kafka.event;


import co.istad.content_service.config.kafka.BaseProducer;
import lombok.Data;

@Data
public class ContentReactedProducer implements BaseProducer {
    private String userId;
    private String contentId;
    private String type;
    private String reactionType;
    private String oldReactionType;// Add this field to match the payload
}

