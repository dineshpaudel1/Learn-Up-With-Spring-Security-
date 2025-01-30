package com.example.LearnUp.System.service.CourseService;

import com.example.LearnUp.System.model.CourseModel.Course;
import com.example.LearnUp.System.model.CourseModel.CourseResponse;
import com.example.LearnUp.System.repository.CategoryRepository.CategoryRepository;
import com.example.LearnUp.System.repository.CourseRepository.CourseRepository;
import com.example.LearnUp.System.repository.photos.CoursePhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImplementation implements CourseService {

    private final CourseRepository courseRepository;

    private final CoursePhotoRepository coursePhotoRepository;

    private final CategoryRepository categoryRepository;



    @Value("${project.image}")
    private String path;


    @Override
    public ResponseEntity<Object> viewCourses(Long id) {
        List<CourseResponse> responseList = courseRepository.teachersAllCourses(id);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAllCourses() {
//
        List<CourseResponse> responseList = courseRepository.allCourses();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }





}
