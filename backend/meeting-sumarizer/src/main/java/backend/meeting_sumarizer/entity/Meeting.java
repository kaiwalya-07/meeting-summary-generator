package backend.meeting_sumarizer.entity;

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
public class Meeting implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long fileId;

    @Column(name="TITLE")
    private String title;

    @Column(name="UPLOADER")
    private String uploader;

    @Column(name="PATH")
    private String filePath;

    @Column(name="TIME")
    private LocalDateTime uploadTime;

    @Lob
    @Column(name = "ACTION")
    private String actionItem;


    @Lob
    @Column(name = "QUESTIONS")
    private String questions;



    @Lob
    @Column(name = "SUMMARY")
    private String summary;


    @Column(name = "STATUS")
    private String status;


}
