package lab6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lab6.entity.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

}