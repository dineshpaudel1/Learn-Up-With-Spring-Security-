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


    @Override
    public ResponseEntity<Object> updateThumbnail(MultipartFile file, Course course) {
        try {
            // Validate file input
            if (file == null || file.isEmpty()) {
                return new ResponseEntity<>(new Response("File is required"), HttpStatus.BAD_REQUEST);
            }


            // Get user from security context
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (!(principal instanceof UserEntity)) {
                return new ResponseEntity<>(new Response("Invalid user authentication"), HttpStatus.UNAUTHORIZED);
            }
            UserEntity user = (UserEntity) principal;

            // Fetch teacher info
            TeacherInfo teacherInfo = teachersRepository.findTeacherById(user.getId());
            if (teacherInfo == null) {
                return new ResponseEntity<>(new Response("Teacher not found"), HttpStatus.NOT_FOUND);
            }

            // Validate instructor ID
            Long id = course.getInstructorId();
            if (!id.equals(teacherInfo.getId())) {
                return new ResponseEntity<>(new Response("Not allowed"), HttpStatus.FORBIDDEN);
            }

            // Fetch course entity
            CourseEntity courseEntity = courseRepository.findById(course.getId())
                    .orElseThrow(() -> new RuntimeException("Course not found with ID: " + course.getId()));

            // Fetch course photo
            CoursePhoto coursePhoto = coursePhotoRepository.findByCourseId(courseEntity.getId());
            if (coursePhoto == null) {
                throw new RuntimeException("Course photo not found for course ID: " + courseEntity.getId());
            }

            // Generate file path
            String originalFileName = file.getOriginalFilename();
            String extension = "";
            if (originalFileName != null && originalFileName.lastIndexOf('.') != -1) {
                extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
            }

            String fileName = "coursethumbnail" + UUID.randomUUID() + extension;
            String filePath = path + File.separator + fileName;

            // Create directory if it doesn't exist
            File f = new File(path);
            if (!f.exists() && !f.mkdirs()) {
                throw new RuntimeException("Failed to create directory: " + path);
            }

            // Save the file
            Files.copy(file.getInputStream(), Paths.get(filePath));

            // Update course photo
            coursePhoto.setCoursePhotoName("/api/photo?fileName=" + fileName);
            coursePhotoRepository.save(coursePhoto);

            return new ResponseEntity<>(new Response("Photo has been updated successfully"), HttpStatus.OK);

        } catch (IOException ex) {
            return new ResponseEntity<>(new Response("Error saving file: " + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(new Response(ex.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response("An unexpected error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    }
