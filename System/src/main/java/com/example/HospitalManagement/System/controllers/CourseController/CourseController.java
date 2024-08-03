package com.example.HospitalManagement.System.controllers.CourseController;

import com.example.HospitalManagement.System.model.CourseModel.Course;
import com.example.HospitalManagement.System.model.CourseModel.CourseResponse;
import com.example.HospitalManagement.System.repository.CourseRepository.CourseRepository;
import com.example.HospitalManagement.System.service.CourseService.CourseService;
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

    @Value("$project.image")
    private String path;

    private final CourseService courseService;

    private final CourseRepository courseRepository;

    @PostMapping("/admin/add")
    public ResponseEntity<Object> addCourse(@RequestParam("course")String data, @RequestParam("file")MultipartFile file)throws IOException {
        Course course = mapper.readValue(data, Course.class);
        return courseService.addCourse(course, file);
    }
    @GetMapping("/my")
    public String output(){
        return "this is output";
    }

    @GetMapping("/users/courses")
    public ResponseEntity<List<CourseResponse>> getCourse(){
        return courseService.getCourse();
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<String>deleteCourse(@PathVariable Long id) throws IOException {
        return courseService.deleteCourse(id);
    }

    @PostMapping("/admin/{courseId}")
    public ResponseEntity<String>updateCoursse(@PathVariable Long courseId, @RequestParam("courses")String data,@RequestParam(value="file",required=false)MultipartFile file) throws IOException{
        Course course = mapper.readValue(data, Course.class);
        return courseService.updateCourse(courseId,course,file);
    }
}
