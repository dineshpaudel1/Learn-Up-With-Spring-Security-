package com.example.LearnUp.System.controllers.CourseController;

import com.example.LearnUp.System.model.CourseModel.Course;
import com.example.LearnUp.System.service.CourseService.CourseService;
import com.example.LearnUp.System.service.CourseService.TeacherCourseService;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TeacherCourseController {

    private final TeacherCourseService teacherCourseService;

    @PostMapping("/teacher/addCourse")
    public ResponseEntity<Object> addCourse(@RequestBody Course course) throws IOException {
        return teacherCourseService.addCourse(course);
    }

    public ResponseEntity<Object> updateThumbnail(@RequestBody Course course){
        return teacherCourseService.updateThumbnail(course);
    }
}
