package ma.ensa.course.services;

import ma.ensa.course.dtos.LessonDto;
import ma.ensa.course.entities.Lesson;
import ma.ensa.course.repositories.LessonRepository;
import org.springframework.stereotype.Service;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public LessonDto updateLesson(LessonDto lessonDto) {
        lessonDto.setDone(true);
        return LessonDto.toDto(this.lessonRepository.save(Lesson.fromDto(lessonDto)));
    }
}
