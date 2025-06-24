package backend.meeting_sumarizer.service;

import backend.meeting_sumarizer.entity.Meeting;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface FileService {

    long uploadFile(MultipartFile file, String uploader, String title) throws IOException;

    Meeting getMeetingById(long fileId);
}
