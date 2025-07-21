package backend.meeting_sumarizer.dto;


import lombok.Data;

@Data
public class SummarizedDataDto {

    private String actionItem;
    private String summary;
    private String questions;
    private Long fileId;
}
