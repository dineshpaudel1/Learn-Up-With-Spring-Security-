package com.example.LearnUp.System.controllers.CourseController;

import com.example.LearnUp.System.model.CourseModel.Course;
import com.example.LearnUp.System.service.CourseService.CourseService;
import com.example.LearnUp.System.service.CourseService.TeacherCourseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TeacherCourseController {

    private final ObjectMapper mapper;

    private final TeacherCourseService teacherCourseService;

    @PostMapping("/teacher/addCourse")
    public ResponseEntity<Object> addCourse(@RequestBody Course course) throws IOException {
        return teacherCourseService.addCourse(course);
    }

    @PatchMapping("/teacher/updateThumbnail")
    public ResponseEntity<Object> updateThumbnail(@RequestParam("data") String data,
                                                  @RequestParam("photo") MultipartFile file)
            throws JsonProcessingException {
        Course course = mapper.readValue(data, Course.class);
        return teacherCourseService.updateThumbnail(file, course);
    }
}
