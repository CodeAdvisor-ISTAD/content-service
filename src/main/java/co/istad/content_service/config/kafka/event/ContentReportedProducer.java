package co.istad.content_service.config.kafka.event;

import co.istad.content_service.config.kafka.BaseProducer;
import lombok.Data;

@Data
public class ContentReportedProducer implements BaseProducer {
    private String userId;
    private String contentId;
    private String type;
    private String slug;
    private String ownerId;
}

//{
//        "contentId": "678e11c0a7224545320e8fcd",
//        "type": "CONTENT",
//        "userId": "123",
//        "slug": "comment/678e11c0a7224545320e8fcd/67904cefa485b71846763474",
//        "ownerId": "123"
//        }