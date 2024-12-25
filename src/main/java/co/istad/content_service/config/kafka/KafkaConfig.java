package co.istad.content_service.config.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    // Topic configuration
    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name("content-created-events-topic")
                .partitions(3) // Increased partitions for scalability
                .replicas(1) // Ensure proper replication based on your cluster setup
                .compact() // Compaction is good for retaining only the latest records with the same key
                .build();
    }

    // Producer configuration
//    @Bean
//    public ProducerFactory<String, Object> producerFactory() {
//        Map<String, Object> config = new HashMap<>();
//        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "167.172.78.79:9092,167.172.78.79:9093,167.172.78.79:9094");
//        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        config.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false); // Keep this false unless polymorphism is needed
//        config.put(ProducerConfig.ACKS_CONFIG, "all"); // Ensures all brokers acknowledge writes
//        config.put(ProducerConfig.RETRIES_CONFIG, 3); // Retry on transient failures
//        config.put(ProducerConfig.LINGER_MS_CONFIG, 1); // Small batching for improved throughput
//        return new DefaultKafkaProducerFactory<>(config, new StringSerializer(), new JsonSerializer<>(getObjectMapper()));
//    }
//
//    // Consumer configuration
//    @Bean
//    public ConsumerFactory<String, Object> consumerFactory() {
//        Map<String, Object> config = new HashMap<>();
//        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "167.172.78.79:9092,167.172.78.79:9093,167.172.78.79:9094");
//        config.put(ConsumerConfig.GROUP_ID_CONFIG, "content-service");
//        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // Start from the earliest offset if group is new
//        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//        config.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
//        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*"); // Allows deserialization of all specified packages
//        config.put(JsonDeserializer.TYPE_MAPPINGS, "content:co.istad.content_service.feature.content.dto.ContentProduceEventRequest");
//        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "co.istad.content_service.feature.content.dto.ContentProduceEventRequest");
//        return new DefaultKafkaConsumerFactory<>(config,
//                new ErrorHandlingDeserializer<>(new StringDeserializer()),
//                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(getObjectMapper())));
//    }
//
//    // KafkaTemplate bean
//    @Bean
//    public KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory<String, Object> producerFactory) {
//        return new KafkaTemplate<>(producerFactory);
//    }
//
//    // ObjectMapper bean for JSON serialization/deserialization
//    private ObjectMapper getObjectMapper() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        return objectMapper;
//    }
}
