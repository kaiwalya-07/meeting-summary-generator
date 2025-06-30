package com.transcriber.transcriber_service.service;

import com.transcriber.transcriber_service.dto.Meeting;
import com.transcriber.transcriber_service.dto.TranscriptionResultDTO;
import com.transcriber.transcriber_service.entity.Transcript;
import com.transcriber.transcriber_service.producer.AudioTranscribedProducer;
import com.transcriber.transcriber_service.repository.AudioTranscribedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class TranscriptionService {
    private final AudioTranscribedProducer producer;

    @Autowired
    private AudioTranscribedRepository repository;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void saveTranscriptToRedis(TranscriptionResultDTO result) {
        String key = "transcript:" + result.getFileId();
        redisTemplate.opsForValue().set(key, result);
    }

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

        producer.sendAudioTranscribedEvent(result);

        repository.save(
                Transcript.builder()
                        .fileId(result.getFileId())
                        .transcriptText(result.getTranscriptText())
                        .transcribedAt(result.getTranscribedAt())
                        .build());

        saveTranscriptToRedis(result);


        log.info("Transcription complete: {}", result);
        return result;
    }

}
