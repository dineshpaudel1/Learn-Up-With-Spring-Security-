package com.example.LearnUp.System.service.CourseService;

import com.example.LearnUp.System.entity.CourseEntity.CourseEntity;
import com.example.LearnUp.System.entity.EnrollmentEntity.EnrollmentEntity;
import com.example.LearnUp.System.model.CourseModel.Course;
import com.example.LearnUp.System.model.CourseModel.CourseResponse;
import com.example.LearnUp.System.model.Response;
import com.example.LearnUp.System.repository.CourseRepository.CourseRepository;
import com.example.LearnUp.System.repository.EnrollmentRepo.EnrollmentRepository;
import com.example.LearnUp.System.service.EnrollmentService.EnrollmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseServiceImplementation implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private EnrollmentService enrollmentService;

    @Value("${project.image}")
    private String path;

    @Override
    public ResponseEntity<Object> addCourse(Course course, MultipartFile file) {
        try {
            // Extract file extension and generate unique file name
            String originalFileName = file.getOriginalFilename();
            String extension = originalFileName != null && originalFileName.contains(".")
                    ? originalFileName.substring(originalFileName.lastIndexOf('.'))
                    : "";
            String fileName = "course" + UUID.randomUUID() + extension;
            String filepath = path + File.separator + fileName;

            // Ensure directory exists and save file
            File f = new File(path);
            if (!f.exists()) {
                f.mkdirs();
            }
            Files.copy(file.getInputStream(), Paths.get(filepath));

            // Create and save the course entity
            CourseEntity courseEntity = CourseEntity.builder()
                    .courseTitle(course.getCourseTitle())
                    .courseDescription(course.getCourseDescription())
                    .category(course.getCategory())
                    .price(course.getPrice())
                    .thumbnail("api/photo?fileName=" + fileName)
                    .rating(course.getRating())
                    .instructor(course.getInstructor())
                    .language(course.getLanguage())
                    .build();

            courseRepository.save(courseEntity);
            return new ResponseEntity<>(new Response("Course has been added successfully"), HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException("Error adding course", e);
        }
    }



    @Override
    public ResponseEntity<List<CourseResponse>> getCourse() {
        List<CourseEntity> courseEntityList = courseRepository.findAll();
        List<CourseResponse> courseList = courseEntityList.stream()
                .map(courseData -> {
                    CourseResponse courseResponse = new CourseResponse();
                    BeanUtils.copyProperties(courseData, courseResponse);
                    return courseResponse;
                }).collect(Collectors.toList());
        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteCourse(Long courseId) {
        try {
            if (courseRepository.existsById(courseId)) {
                courseRepository.deleteById(courseId);
                return new ResponseEntity<>("Course deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deleting course", e);
        }
    }

    @Override
    public ResponseEntity<String> updateCourse(Long courseId, Course course, MultipartFile file) {
        try {
            Optional<CourseEntity> optionalCourse = courseRepository.findById(courseId);
            if (optionalCourse.isPresent()) {
                CourseEntity existingCourse = optionalCourse.get();

                // Update fields
                existingCourse.setCourseTitle(course.getCourseTitle());
                existingCourse.setCourseDescription(course.getCourseDescription());
                existingCourse.setCategory(course.getCategory());
                existingCourse.setPrice(course.getPrice());
                existingCourse.setRating(course.getRating());
                existingCourse.setInstructor(course.getInstructor());
                existingCourse.setLanguage(course.getLanguage());

                // Update thumbnail if a new file is provided
                if (file != null && !file.isEmpty()) {
                    String originalFileName = file.getOriginalFilename();
                    String extension = originalFileName != null && originalFileName.contains(".")
                            ? originalFileName.substring(originalFileName.lastIndexOf('.'))
                            : "";
                    String fileName = "course" + UUID.randomUUID() + extension;
                    String filepath = path + File.separator + fileName;

                    Files.copy(file.getInputStream(), Paths.get(filepath));
                    existingCourse.setThumbnail("api/photo?fileName=" + fileName);
                }

                courseRepository.save(existingCourse);
                return new ResponseEntity<>("Course updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Course not found: " + courseId, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error while updating course: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<CourseEntity> recommendCourses(Long courseId) {
        // Fetch the course that the user clicked on
        Optional<CourseEntity> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            CourseEntity clickedCourse = course.get();

            // Fetch other courses with similar category, instructor, and language
            List<CourseEntity> recommendedCourses = new ArrayList<>();
            recommendedCourses.addAll(courseRepository.findByCategory(clickedCourse.getCategory()));
            recommendedCourses.addAll(courseRepository.findByInstructor(clickedCourse.getInstructor()));

            // Filter out the clicked course from the recommendations (optional)
            recommendedCourses.removeIf(c -> c.getId() == clickedCourse.getId());

            return recommendedCourses;
        } else {
            return new ArrayList<>();
        }
    }
}
