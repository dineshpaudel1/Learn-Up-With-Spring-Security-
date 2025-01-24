package com.example.LearnUp.System.service.CourseService;

import com.example.LearnUp.System.entity.CourseEntity.CourseEntity;
import com.example.LearnUp.System.model.CourseModel.Course;
import com.example.LearnUp.System.model.CourseModel.CourseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public interface CourseService {

    // Adds a new course with details and thumbnail image file
    ResponseEntity<Object> addCourse(Course course);

    // Retrieves a list of all courses with response format
    ResponseEntity<List<CourseResponse>> getCourse();

    // Deletes a course by its ID
    ResponseEntity<String> deleteCourse(Long courseId);

    // Updates course details by ID, including optional new thumbnail image file
    ResponseEntity<String> updateCourse(Long courseId, Course course, MultipartFile file);

//    public List<CourseEntity> recommendCourses(Long courseId);
    public ResponseEntity<CourseResponse> getCourseByTitle(String courseTitle);
}
