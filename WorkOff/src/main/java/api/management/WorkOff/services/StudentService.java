package api.management.WorkOff.services;

import api.management.WorkOff.models.Makeup;
import api.management.WorkOff.models.Student;
import api.management.WorkOff.repositories.MakeupRepository;
import api.management.WorkOff.repositories.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final MakeupRepository makeupRepository;

    public StudentService(StudentRepository studentRepository, MakeupRepository makeupRepository) {
        this.studentRepository = studentRepository;
        this.makeupRepository = makeupRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public ResponseEntity<Student> getStudent(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            return ResponseEntity.ok(optionalStudent.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    public ResponseEntity<Student> createStudent(Student student) {
        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    public ResponseEntity<Student> updateStudent(Long id, Student student) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();
            existingStudent.setName(student.getName());
            existingStudent.setGroupName(student.getGroupName());
            existingStudent.setContactInfo(student.getContactInfo());
            Student updatedStudent = studentRepository.save(existingStudent);
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    public ResponseEntity<?> deleteStudent(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            studentRepository.delete(student);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    public ResponseEntity<List<Makeup>> getStudentMakeups(Long id) {
        List<Makeup> studentMakeups = makeupRepository.findByStudentId(id);

        if (studentMakeups.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }

        return ResponseEntity.ok(studentMakeups);
    }
}

