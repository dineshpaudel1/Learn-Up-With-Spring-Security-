package com.example.LearnUp.System.repository.CourseRepository;

import com.example.LearnUp.System.entity.CourseEntity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity,Long> {
}
