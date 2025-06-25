package com.transcriber.transcriber_service.dto;


import lombok.Data;
import java.time.LocalDateTime;


@Data
public class TranscriptionResultDTO {
    private Long fileId;
    private String transcriptText;
    private LocalDateTime transcribedAt;
}
