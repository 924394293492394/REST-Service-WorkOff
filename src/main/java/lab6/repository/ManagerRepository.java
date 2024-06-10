package lab6.repository;

import lab6.entity.Manager;
import lab6.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager findByUsername(String username);
}