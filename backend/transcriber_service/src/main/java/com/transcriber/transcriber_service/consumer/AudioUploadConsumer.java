package com.transcriber.transcriber_service.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transcriber.transcriber_service.dto.Meeting;
import com.transcriber.transcriber_service.service.TranscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AudioUploadConsumer {

    private final TranscriptionService transcriptionService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "audio_uploaded", groupId = "transcriber-group")
    public void consume(String message) {
        try {
            Meeting meeting = objectMapper.readValue(message, Meeting.class);
            log.info("Consumed message for fileId: {}", meeting.getFileId());
            transcriptionService.simulateTranscription(meeting);
        } catch (Exception e) {
            log.error("Error parsing or transcribing: ", e);
        }
    }
}