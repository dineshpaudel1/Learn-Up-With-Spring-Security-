package com.example.LearnUp.System.service.CourseService;

import com.example.LearnUp.System.entity.CategoryEntity.CategoryEntity;
import com.example.LearnUp.System.entity.CourseEntity.CourseEntity;
import com.example.LearnUp.System.entity.CourseEntity.CoursePhoto;
import com.example.LearnUp.System.entity.EnrollmentEntity.EnrollmentEntity;
import com.example.LearnUp.System.entity.TeacherEntity.TeacherEntity;
import com.example.LearnUp.System.model.CourseModel.Course;
import com.example.LearnUp.System.model.CourseModel.CourseResponse;
import com.example.LearnUp.System.model.Response;
import com.example.LearnUp.System.repository.CategoryRepository.CategoryRepository;
import com.example.LearnUp.System.repository.CourseRepository.CourseRepository;
import com.example.LearnUp.System.repository.EnrollmentRepo.EnrollmentRepository;
import com.example.LearnUp.System.repository.Teachers.TeachersRepository;
import com.example.LearnUp.System.repository.photos.CoursePhotoRepository;
import com.example.LearnUp.System.service.EnrollmentService.EnrollmentService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class CourseServiceImplementation implements CourseService {

    private final CourseRepository courseRepository;

    private final CoursePhotoRepository coursePhotoRepository;

    private final CategoryRepository categoryRepository;

    private final TeachersRepository teachersRepository;

    @Value("${project.image}")
    private String path;

    @Override
    public ResponseEntity<Object> addCourse(Course course) {

        CategoryEntity category = categoryRepository.findByCategoryName(course.getCategory())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        TeacherEntity teacherEntity = teachersRepository.findById(course.getInstructorId()).get();

            CourseEntity courseEntity = CourseEntity.builder()
                    .courseTitle(course.getCourseTitle())
                    .courseDescription(course.getCourseDescription())
                    .price(course.getPrice())
                    .category(category)
                    .teacher(teacherEntity)
                    .language(course.getLanguage())
                    .build();

            courseRepository.save(courseEntity);


            CoursePhoto coursePhoto = CoursePhoto.builder()
                    .coursePhotoName("/api/photo?fileName=category.png")
                    .course(courseEntity)
                    .build();
            coursePhotoRepository.save(coursePhoto);
            return new ResponseEntity<>(new Response("Course has been added successfully"), HttpStatus.OK);

    }



    @Override
    public ResponseEntity<List<CourseResponse>> getCourse() {
//        List<CourseEntity> courseEntityList = courseRepository.findAll();
//        List<CourseResponse> courseList = courseEntityList.stream()
//                .map(courseData -> {
//                    CourseResponse courseResponse = new CourseResponse();
//                    BeanUtils.copyProperties(courseData, courseResponse);
//                    return courseResponse;
//                }).collect(Collectors.toList());
//        return new ResponseEntity<>(courseList, HttpStatus.OK);
        return null;
    }

    @Override
    public ResponseEntity<String> deleteCourse(Long courseId) {
//        try {
//            if (courseRepository.existsById(courseId)) {
//                courseRepository.deleteById(courseId);
//                return new ResponseEntity<>("Course deleted successfully", HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("Error deleting course", e);
//        }
        return null;
    }

    @Override
    public ResponseEntity<String> updateCourse(Long courseId, Course course, MultipartFile file) {
//        try {
//            Optional<CourseEntity> optionalCourse = courseRepository.findById(courseId);
//            if (optionalCourse.isPresent()) {
//                CourseEntity existingCourse = optionalCourse.get();
//
//                // Update fields
//                existingCourse.setCourseTitle(course.getCourseTitle());
//                existingCourse.setCourseDescription(course.getCourseDescription());
//                existingCourse.setCategory(course.getCategory());
//                existingCourse.setPrice(course.getPrice());
//                existingCourse.setInstructor(course.getInstructor());
//                existingCourse.setLanguage(course.getLanguage());
//                existingCourse.setVideoLink(course.getVideoLink());
//
//                // Update thumbnail if a new file is provided
//                if (file != null && !file.isEmpty()) {
//                    String originalFileName = file.getOriginalFilename();
//                    String extension = originalFileName != null && originalFileName.contains(".")
//                            ? originalFileName.substring(originalFileName.lastIndexOf('.'))
//                            : "";
//                    String fileName = "course" + UUID.randomUUID() + extension;
//                    String filepath = path + File.separator + fileName;
//
//                    Files.copy(file.getInputStream(), Paths.get(filepath));
//                    existingCourse.setThumbnail("api/photo?fileName=" + fileName);
//                }
//
//                courseRepository.save(existingCourse);
//                return new ResponseEntity<>("Course updated successfully", HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("Course not found: " + courseId, HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>("Error while updating course: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
        return null;
    }

    public ResponseEntity<CourseResponse> getCourseByTitle(String courseTitle) {
//        Optional<CourseEntity> courseEntity = courseRepository.findByCourseTitle(courseTitle);
//        if (courseEntity.isPresent()) {
//            CourseResponse courseResponse = new CourseResponse();
//            BeanUtils.copyProperties(courseEntity.get(), courseResponse);
//            return new ResponseEntity<>(courseResponse, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
        return null;
    }

}
