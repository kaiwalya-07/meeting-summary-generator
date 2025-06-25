package com.transcriber.transcriber_service.service;

import com.transcriber.transcriber_service.dto.Meeting;
import com.transcriber.transcriber_service.dto.TranscriptionResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class TranscriptionService {
    public TranscriptionResultDTO simulateTranscription(Meeting meeting) {
        log.info("Transcribing file: {}", meeting.getFilePath());

        try {
            Thread.sleep(2000); // simulate delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        TranscriptionResultDTO result = new TranscriptionResultDTO();
        result.setFileId(meeting.getFileId());
        result.setTranscriptText("Hello, this is a sample transcript for '" + meeting.getTitle() + "'");
        result.setTranscribedAt(LocalDateTime.now());

        log.info("Transcription complete: {}", result);
        return result;
    }

}
