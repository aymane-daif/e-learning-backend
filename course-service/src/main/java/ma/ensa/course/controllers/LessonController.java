package ma.ensa.course.controllers;

import ma.ensa.course.dtos.LessonDto;
import ma.ensa.course.services.LessonService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses/lessons")
public class LessonController {
    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PatchMapping(path = "/{userId}")
    public LessonDto updateLesson(@RequestBody LessonDto lessonDto, @PathVariable Long userId) {
        return this.lessonService.updateLesson(lessonDto);
    }
}
