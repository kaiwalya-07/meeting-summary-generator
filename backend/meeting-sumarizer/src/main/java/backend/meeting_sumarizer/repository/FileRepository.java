package backend.meeting_sumarizer.repository;

import backend.meeting_sumarizer.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FileRepository extends JpaRepository<Meeting,Long> {
}
