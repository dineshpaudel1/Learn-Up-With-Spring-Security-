package com.example.LearnUp.System.service.CourseService;

import com.example.LearnUp.System.entity.CategoryEntity.CategoryEntity;
import com.example.LearnUp.System.entity.CourseEntity.CourseEntity;
import com.example.LearnUp.System.entity.CourseEntity.CoursePhoto;
import com.example.LearnUp.System.entity.TeacherEntity.TeacherEntity;
import com.example.LearnUp.System.entity.UserEntity.PhotosEntity;
import com.example.LearnUp.System.entity.UserEntity.UserEntity;
import com.example.LearnUp.System.model.CourseModel.Course;
import com.example.LearnUp.System.model.CourseModel.CourseResponse;
import com.example.LearnUp.System.model.Response;
import com.example.LearnUp.System.model.teacher.TeacherInfo;
import com.example.LearnUp.System.repository.CategoryRepository.CategoryRepository;
import com.example.LearnUp.System.repository.CourseRepository.CourseRepository;
import com.example.LearnUp.System.repository.Teachers.TeachersRepository;
import com.example.LearnUp.System.repository.photos.CoursePhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherCourseServiceImpl implements TeacherCourseService{

    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;
    private final CoursePhotoRepository coursePhotoRepository;
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


    }
