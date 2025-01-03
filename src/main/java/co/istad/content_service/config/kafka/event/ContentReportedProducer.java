package co.istad.content_service.config.kafka.event;

import co.istad.content_service.config.kafka.BaseProducer;
import lombok.Data;

@Data
public class ContentReportedProducer implements BaseProducer {
    private String userId;
    private String contentId;
    private String type;
    private String body; // Add this field to match the payload
}
