package com.transcriber.transcriber_service.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transcriber.transcriber_service.dto.TranscriptionResultDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class AudioTranscribedProducer {
    KafkaTemplate<String,String> kafkaTemplate;

    @Value("${kafka.topic.audio-transcribed}")
    private String audioTranscribed;

    public AudioTranscribedProducer(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;
    }

    public void sendAudioTranscribedEvent(TranscriptionResultDTO dto){

        ObjectMapper objectMapper=new ObjectMapper();
        String message= null;
        try {
            message = objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        kafkaTemplate.send(audioTranscribed,message);
    }
}
