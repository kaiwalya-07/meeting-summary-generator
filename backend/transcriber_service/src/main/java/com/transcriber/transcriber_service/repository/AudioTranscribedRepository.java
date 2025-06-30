package com.transcriber.transcriber_service.repository;

import com.transcriber.transcriber_service.entity.Transcript;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudioTranscribedRepository extends JpaRepository<Transcript,Long>{
}
