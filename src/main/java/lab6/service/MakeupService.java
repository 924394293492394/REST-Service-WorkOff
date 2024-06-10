package lab6.service;

import lab6.entity.Lesson;
import lab6.entity.Makeup;
import lab6.entity.Student;
import lab6.repository.LessonRepository;
import lab6.repository.MakeupRepository;
import lab6.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MakeupService {
    private final MakeupRepository makeupRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;

    public MakeupService(MakeupRepository makeupRepository, StudentRepository studentRepository, LessonRepository lessonRepository) {
        this.makeupRepository = makeupRepository;
        this.studentRepository = studentRepository;
        this.lessonRepository = lessonRepository;
    }

    public List<Makeup> getAllMakeups() {
        return makeupRepository.findAll();
    }

    public ResponseEntity<Makeup> getMakeup(Long id) {
        Optional<Makeup> optionalMakeup = makeupRepository.findById(id);
        if (optionalMakeup.isPresent()) {
            return ResponseEntity.ok(optionalMakeup.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    public ResponseEntity<Makeup> createMakeup(Makeup makeup) {
        Optional<Student> optionalStudent = studentRepository.findById(makeup.getStudent().getId());
        Optional<Lesson> optionalLesson = lessonRepository.findById(makeup.getLesson().getId());
        if (optionalStudent.isPresent() && optionalLesson.isPresent()) {
            makeup.setStudent(optionalStudent.get());
            makeup.setLesson(optionalLesson.get());
            Makeup savedMakeup = makeupRepository.save(makeup);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMakeup);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    public ResponseEntity<Makeup> updateMakeup(Long id, Makeup makeup) {
        Optional<Makeup> optionalMakeup = makeupRepository.findById(id);
        if (optionalMakeup.isPresent()) {
            Makeup existingMakeup = optionalMakeup.get();
            Optional<Student> optionalStudent = studentRepository.findById(makeup.getStudent().getId());
            Optional<Lesson> optionalLesson = lessonRepository.findById(makeup.getLesson().getId());
            if (optionalStudent.isPresent() && optionalLesson.isPresent()) {
                existingMakeup.setStudent(optionalStudent.get());
                existingMakeup.setLesson(optionalLesson.get());
                existingMakeup.setMakeupDate(makeup.getMakeupDate());
                existingMakeup.setComment(makeup.getComment());
                Makeup updatedMakeup = makeupRepository.save(existingMakeup);
                return ResponseEntity.ok(updatedMakeup);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    public ResponseEntity<?> deleteMakeup(Long id) {
        Optional<Makeup> optionalMakeup = makeupRepository.findById(id);
        if (optionalMakeup.isPresent()) {
            Makeup makeup = optionalMakeup.get();
            makeupRepository.delete(makeup);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }
}
