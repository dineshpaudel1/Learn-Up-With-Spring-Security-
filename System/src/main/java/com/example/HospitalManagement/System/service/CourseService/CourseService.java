package com.example.HospitalManagement.System.service.CourseService;


import com.example.HospitalManagement.System.model.CourseModel.Course;
import com.example.HospitalManagement.System.model.CourseModel.CourseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface CourseService {

    ResponseEntity<Object>addCourse(Course course, MultipartFile file);

    ResponseEntity<List<CourseResponse>>getCourse();

    ResponseEntity<String>deleteCourse(Long courseId);

    ResponseEntity<String>updateCourse(Long courseId, Course course, MultipartFile file);
}