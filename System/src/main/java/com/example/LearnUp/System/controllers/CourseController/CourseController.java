package com.example.LearnUp.System.controllers.CourseController;

import com.example.LearnUp.System.entity.CourseEntity.CourseEntity;
import com.example.LearnUp.System.model.CourseModel.Course;
import com.example.LearnUp.System.model.CourseModel.CourseResponse;
import com.example.LearnUp.System.service.CourseService.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CourseController {

    private final ObjectMapper mapper;

    @Value("${project.image}")
    private String path;

    private final CourseService courseService;



    @PostMapping("/teacher/addCourse")
    public ResponseEntity<Object> addCourse(@RequestBody Course course) throws IOException {
        return courseService.addCourse(course);
    }

    @GetMapping("/users/courses")
    public ResponseEntity<List<CourseResponse>> getCourse() {
        return courseService.getCourse();
    }

    @DeleteMapping("/teacher/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) throws IOException {
        return courseService.deleteCourse(id);
    }

    @PostMapping("/teacher/{courseId}")
    public ResponseEntity<String> updateCourse(
            @PathVariable Long courseId,
            @RequestParam("course") String data,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IOException {
        Course course = mapper.readValue(data, Course.class);
        return courseService.updateCourse(courseId, course, file);
    }

    @GetMapping("/users/{courseTitle}")
    public ResponseEntity<CourseResponse> getCourseByTitle(@PathVariable String courseTitle) {
        return courseService.getCourseByTitle(courseTitle);
    }


}
