package co.istad.content_service.config.kafka;

import co.istad.content_service.config.kafka.event.CommentProducer;
import co.istad.content_service.config.kafka.event.ContentReactedProducer;
import co.istad.content_service.config.kafka.event.ContentReportedProducer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

@Slf4j
public class BaseProducerDeserializer implements Deserializer<BaseProducer> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // No configuration needed
    }

//    @Override
//    public BaseProducer deserialize(String topic, byte[] data) {
//        try {
//            if (data == null) return null;
//            JsonNode node = objectMapper.readTree(data);
//
//            if (!node.has("type")) {
//                throw new IllegalArgumentException("Missing 'type' field in JSON");
//            }
//
//            String type = node.get("type").asText();
//            if (!node.has("type")) {
//                throw new IllegalArgumentException("Missing 'type' field in JSON");
//            }
//
//            return switch (type) {
//                case "COMMENT" -> objectMapper.treeToValue(node, CommentProducer.class);
//                case "LIKE", "DISLIKE" -> objectMapper.treeToValue(node, ContentReactedProducer.class);
//                case "REPORT" -> objectMapper.treeToValue(node, ContentReportedProducer.class);
//                default -> {
//                    log.warn("Unknown type: {}", type);
//                    throw new IllegalArgumentException("Unknown type: " + type);
//                }
//            };
//        } catch (Exception e) {
//            throw new RuntimeException("Deserialization failed", e);
//        }
//    }
@Override
public BaseProducer deserialize(String topic, byte[] data) {
    try {
        if (data == null) {
            log.warn("Received null data for topic: {}", topic);
            return null;
        }

        JsonNode node = objectMapper.readTree(data);
        if (!node.has("type")) {
            throw new IllegalArgumentException("Missing 'type' field in JSON");
        }

        String type = node.get("type").asText();
        return switch (type) {
            case "COMMENT","CREATED" -> objectMapper.treeToValue(node, CommentProducer.class);
            case "REACTED" -> objectMapper.treeToValue(node, ContentReactedProducer.class);
            case "CONTENT" -> objectMapper.treeToValue(node, ContentReportedProducer.class);
            default -> {
                log.warn("Unknown type: {}", type);
                throw new IllegalArgumentException("Unknown type: " + type);
            }
        };
    } catch (Exception e) {
        log.error("Deserialization failed for topic: {}", topic, e);
        throw new RuntimeException("Deserialization failed", e);
    }
}

    @Override
    public void close() {
        // No cleanup necessary
        //
    }
}
