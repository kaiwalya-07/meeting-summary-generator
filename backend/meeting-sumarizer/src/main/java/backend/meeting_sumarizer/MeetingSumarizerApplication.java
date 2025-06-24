package backend.meeting_sumarizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MeetingSumarizerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetingSumarizerApplication.class, args);
	}

}
