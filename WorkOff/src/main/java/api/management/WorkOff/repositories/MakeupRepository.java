package api.management.WorkOff.repositories;

import api.management.WorkOff.models.Makeup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MakeupRepository extends JpaRepository<Makeup, Long> {
    List<Makeup> findByStudentId(Long studentId);
    List<Makeup> findByLessonId(Long lessonId);
}