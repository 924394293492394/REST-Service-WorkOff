package lab6.repository;

import java.util.List;

import lab6.entity.Makeup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MakeupRepository extends JpaRepository<Makeup, Long> {
	List<Makeup> findByStudentId(Long studentId);
	List<Makeup> findByLessonId(Long lessonId);
}
