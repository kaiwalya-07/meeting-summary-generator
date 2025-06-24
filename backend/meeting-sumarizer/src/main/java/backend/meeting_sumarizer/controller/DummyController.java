package backend.meeting_sumarizer.controller;

import backend.meeting_sumarizer.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class DummyController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/send")
    public ResponseEntity<String> sendDummyEvent(@RequestParam String message) {
        kafkaProducerService.sendAudioUploadedEvent(message);
        return ResponseEntity.ok("Sent to Kafka: " + message);
    }
}
