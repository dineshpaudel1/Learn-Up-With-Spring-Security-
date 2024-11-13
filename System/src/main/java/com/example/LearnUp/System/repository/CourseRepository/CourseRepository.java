package com.example.LearnUp.System.repository.CourseRepository;

import com.example.LearnUp.System.entity.CourseEntity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<CourseEntity,Long> {
    List<CourseEntity> findByCategory(String category);
    List<CourseEntity> findByInstructor(String instructor);
    Optional<CourseEntity> findByCourseTitle(String courseTitle);

}
