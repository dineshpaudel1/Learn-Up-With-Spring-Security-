package com.example.LearnUp.System.service.CourseService;

import com.example.LearnUp.System.model.CourseModel.Course;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TeacherCourseService {

    ResponseEntity<Object> addCourse(Course course);

    ResponseEntity<Object> updateThumbnail(Course course);
}
