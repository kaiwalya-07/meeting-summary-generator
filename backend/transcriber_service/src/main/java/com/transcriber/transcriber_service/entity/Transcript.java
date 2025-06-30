package com.transcriber.transcriber_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transcript implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(name="FILE_ID")
    private long fileId;

    @Column(name="TRANSCRIPT")
    private String transcriptText;

    @Column(name="TIME")
    private LocalDateTime transcribedAt;
}
