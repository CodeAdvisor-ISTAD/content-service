package co.istad.content_service.config.kafka;

import co.istad.content_service.config.kafka.event.CommentProducer;
import co.istad.content_service.config.kafka.event.ContentReactedProducer;
import co.istad.content_service.config.kafka.event.ContentReportedProducer;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CommentProducer.class, names ={ "COMMENT", "CREATED"}),
        @JsonSubTypes.Type(value = ContentReactedProducer.class, name = "REACTED"),
        @JsonSubTypes.Type(value = ContentReportedProducer.class, name = "CONTENT")
})
public interface BaseProducer {

}
