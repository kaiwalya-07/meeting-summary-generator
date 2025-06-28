package backend.meeting_sumarizer.service;

import backend.meeting_sumarizer.entity.Meeting;
import backend.meeting_sumarizer.repository.FileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class FileServiceImpl implements FileService{
    @Autowired
    private FileRepository fileRepository;



    @Override
    @CachePut(value = "meeting",key = "#result.fileId")
    @Transactional
    public Meeting uploadFile(MultipartFile file, String uploader, String title) throws IOException {
        String uploadDir = "uploads/";
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);

        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());

        Meeting meeting = Meeting.builder()
                .title(title)
                .uploader(uploader)
                .filePath(filePath.toString())
                .uploadTime(LocalDateTime.now())
                .build();

        fileRepository.save(meeting);
        return meeting;
    }

    @Override
    @Cacheable(value = "meeting",key = "#fileId")
    public Meeting getMeetingById(long fileId) {
        return fileRepository.findById(fileId).orElseThrow(()->new RuntimeException("File not found"));
    }
}
