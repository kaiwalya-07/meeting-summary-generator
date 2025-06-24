package backend.meeting_sumarizer.controller;

import backend.meeting_sumarizer.entity.Meeting;
import backend.meeting_sumarizer.service.FileService;
import backend.meeting_sumarizer.service.KafkaProducerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/upload")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> uploadFile(
            @RequestParam("file")MultipartFile file,
            @RequestParam("uploader") String uploader,
            @RequestParam("title") String title
            ){
        try{
            long fileId=fileService.uploadFile(file,uploader,title);

            Meeting meeting=fileService.getMeetingById(fileId);

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            String json = mapper.writeValueAsString(meeting);


            kafkaProducerService.sendAudioUploadedEvent(json);

            return new ResponseEntity<>(fileId, HttpStatus.CREATED);
        }catch (Exception ex){
            log.error("Error: ",ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
