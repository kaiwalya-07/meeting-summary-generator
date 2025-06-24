package com.transcriber.transcriber_service.service;

import com.transcriber.transcriber_service.dto.Meeting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TranscriptionService {
    public void simulateTranscription(Meeting meeting) {
        log.info("Transcribing file: {}", meeting.getFilePath());

        try {
            Thread.sleep(2000); // simulate delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        log.info("Transcription complete for fileId: {}", meeting.getFileId());
        log.info("Transcript (dummy): Hello, this is a sample transcript for '{}'", meeting.getTitle());
    }
}
