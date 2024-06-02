package api.management.WorkOff.services;

import api.management.WorkOff.models.Lesson;
import api.management.WorkOff.models.Makeup;
import api.management.WorkOff.repositories.LessonRepository;
import api.management.WorkOff.repositories.MakeupRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final MakeupRepository makeupRepository;

    public LessonService(LessonRepository lessonRepository, MakeupRepository makeupRepository) {
        this.lessonRepository = lessonRepository;
        this.makeupRepository = makeupRepository;
    }

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public ResponseEntity<Lesson> getLesson(Long id) {
        Optional<Lesson> optionalLesson = lessonRepository.findById(id);
        if (optionalLesson.isPresent()) {
            return ResponseEntity.ok(optionalLesson.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    public ResponseEntity<Lesson> createLesson(Lesson lesson) {
        Lesson savedLesson = lessonRepository.save(lesson);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLesson);
    }

    public ResponseEntity<Lesson> updateLesson(Long id, Lesson lesson) {
        Optional<Lesson> optionalLesson = lessonRepository.findById(id);
        if (optionalLesson.isPresent()) {
            Lesson existingLesson = optionalLesson.get();
            existingLesson.setName(lesson.getName());
            existingLesson.setDate(lesson.getDate());
            existingLesson.setTeacher(lesson.getTeacher());
            Lesson updatedLesson = lessonRepository.save(existingLesson);
            return ResponseEntity.ok(updatedLesson);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    public ResponseEntity<?> deleteLesson(Long id) {
        Optional<Lesson> optionalLesson = lessonRepository.findById(id);
        if (optionalLesson.isPresent()) {
            Lesson lesson = optionalLesson.get();
            lessonRepository.delete(lesson);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    public ResponseEntity<List<Makeup>> getLessonMakeups(Long id) {
        List<Makeup> lessonMakeups = makeupRepository.findByLessonId(id);

        if (lessonMakeups.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }

        return ResponseEntity.ok(lessonMakeups);
    }
}
