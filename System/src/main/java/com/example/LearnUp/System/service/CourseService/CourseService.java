package com.example.LearnUp.System.service.CourseService;

import com.example.LearnUp.System.model.CourseModel.Course;
import com.example.LearnUp.System.model.CourseModel.CourseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface CourseService {

    // Adds a new course with details and thumbnail image file
    ResponseEntity<Object> viewCourses(Long id);

    ResponseEntity<Object> getAllCourses();



}
