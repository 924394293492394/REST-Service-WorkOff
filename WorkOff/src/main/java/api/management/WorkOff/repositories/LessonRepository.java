package api.management.WorkOff.repositories;

import api.management.WorkOff.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

}