package com.transcriber.transcriber_service.dto;


import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Meeting {
    private Long fileId;
    private String title;
    private String uploader;
    private String filePath;
    private LocalDateTime uploadTime;
}
