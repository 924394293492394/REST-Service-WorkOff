package api.management.WorkOff.repositories;

import api.management.WorkOff.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
