package backend.meeting_sumarizer.consumer;


import backend.meeting_sumarizer.dto.SummarizedDataDto;
import backend.meeting_sumarizer.entity.Meeting;
import backend.meeting_sumarizer.repository.FileRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MeetingSummarizedConsumer {

    private final FileRepository fileRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "meeting_summarized", groupId = "summarizer-backend-group")
    public void consume(String message){
        try {
            SummarizedDataDto summarizedDataDto=objectMapper.readValue(message,SummarizedDataDto.class);
            Long id=summarizedDataDto.getFileId();
            Meeting meeting=fileRepository.findById(id).orElseThrow(()->new RuntimeException("File not found for id"+id));

            String actionItem = summarizedDataDto.getActionItem();
            String summary=summarizedDataDto.getSummary();
            String questions=summarizedDataDto.getQuestions();

            meeting.setSummary(summary);
            meeting.setActionItem(actionItem);
            meeting.setQuestions(questions);
            meeting.setStatus("completed");

            fileRepository.save(meeting);

        }catch (Exception e){
             log.error("UNHANDLED Exception",e);
        }
    }
}
