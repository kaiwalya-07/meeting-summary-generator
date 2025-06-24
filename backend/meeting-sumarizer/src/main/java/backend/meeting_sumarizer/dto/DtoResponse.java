package backend.meeting_sumarizer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DtoResponse {
    private long id;
    private String title;
    private String name;
    private String path;
    private LocalDateTime time;
}
