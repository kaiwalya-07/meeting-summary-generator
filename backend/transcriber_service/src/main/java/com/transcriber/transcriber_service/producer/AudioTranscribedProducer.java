package com.transcriber.transcriber_service.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.transcriber.transcriber_service.dto.TranscriptionResultDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class AudioTranscribedProducer {

    @Value("${kafka.topic.audio-transcribed}")
    private String audioTranscribed;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;


    public AudioTranscribedProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public void sendAudioTranscribedEvent(TranscriptionResultDTO dto) {
        try {
            String message = objectMapper.writeValueAsString(dto);
            kafkaTemplate.send(audioTranscribed, message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
