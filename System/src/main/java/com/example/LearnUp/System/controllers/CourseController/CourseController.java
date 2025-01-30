package com.example.LearnUp.System.controllers.CourseController;

import com.example.LearnUp.System.model.CourseModel.CourseResponse;
import com.example.LearnUp.System.service.CourseService.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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



    @GetMapping("/users/courses")
    public ResponseEntity<Object> getAllCourses() {

        return courseService.getAllCourses();
    }

    @GetMapping("/users/viewCourses/{id}")
    public ResponseEntity<Object> getCoursesByTeacher(@PathVariable("id") Long id) throws IOException {

        return courseService.viewCourses(id);
    }


}

