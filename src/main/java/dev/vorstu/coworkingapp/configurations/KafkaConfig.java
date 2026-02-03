package dev.vorstu.coworkingapp.configurations;

import dev.vorstu.coworkingapp.jwt.dto.JwtRequest;
import dev.vorstu.coworkingapp.jwt.dto.JwtResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

import java.util.UUID;

@Configuration
public class KafkaConfig {

    @Value("${kafka.auth.response.topic}")
    private String authResponseTopic;

    @Bean
    public KafkaMessageListenerContainer<UUID, JwtResponse> kafkaMessageListenerContainer(
            ConsumerFactory<UUID, JwtResponse> consumerFactory
    ) {
        return new KafkaMessageListenerContainer<>(
                consumerFactory,
                new ContainerProperties(authResponseTopic)
        );
    }

    @Bean
    public ReplyingKafkaTemplate<UUID, JwtRequest, JwtResponse> replyingKafkaTemplate(
            ProducerFactory<UUID, JwtRequest> producerFactory,
            KafkaMessageListenerContainer<UUID, JwtResponse> kafkaMessageListenerContainer
    ) {
        return new ReplyingKafkaTemplate<>(producerFactory, kafkaMessageListenerContainer);
    }

    @Bean
    public KafkaTemplate<UUID, JwtResponse> kafkaTemplate(
            ProducerFactory<UUID, JwtResponse> producerFactory
    ) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<UUID, JwtRequest>> kafkaListenerContainerFactory(
            ConsumerFactory<UUID, JwtRequest> consumerFactory,
            KafkaTemplate<UUID, JwtResponse> kafkaTemplate
    ) {
        ConcurrentKafkaListenerContainerFactory<UUID, JwtRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setReplyTemplate(kafkaTemplate);
        return factory;
    }

}
