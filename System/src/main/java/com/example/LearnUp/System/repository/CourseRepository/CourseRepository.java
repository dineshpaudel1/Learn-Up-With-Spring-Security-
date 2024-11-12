package com.example.LearnUp.System.repository.CourseRepository;

import com.example.LearnUp.System.entity.CourseEntity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity,Long> {
    List<CourseEntity> findByCategory(String category);
    List<CourseEntity> findByInstructor(String instructor);
    List<CourseEntity> findByLanguage(String language);
}
