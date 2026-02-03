package dev.vorstu.coworkingapp.kafka;

import dev.vorstu.coworkingapp.jwt.dto.JwtRequest;
import dev.vorstu.coworkingapp.jwt.dto.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class KafkaAuthReplying {

    private final ReplyingKafkaTemplate<UUID, JwtRequest, JwtResponse> replyingKafkaTemplate;

    private final KafkaTemplate<UUID, JwtResponse> kafkaTemplate;

    @Value("${kafka.auth.request.topic}")
    private String authRequestTopic;

    public RequestReplyFuture<UUID, JwtRequest, JwtResponse> sendRequest(JwtRequest jwtRequest) {
        ProducerRecord<UUID, JwtRequest> producerRecord = new ProducerRecord<>(authRequestTopic, jwtRequest);
        return replyingKafkaTemplate.sendAndReceive(producerRecord);
    }

}
