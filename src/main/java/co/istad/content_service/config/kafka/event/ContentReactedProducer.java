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
    private String ownerId;
    private String slug;
}

//{
//        "contentId": "678e11c0a7224545320e8fcd",
//        "type": "REACTED",
//        "userId": "6783b16f1b533f163cd7460d",
//        "reactionType": "love",
//        "oldReactionType": "fire",
//        "ownerId": "b3ee9ec3-be2f-401d-89fc-6f3956efcfc4",
//        "slug": null
//        }