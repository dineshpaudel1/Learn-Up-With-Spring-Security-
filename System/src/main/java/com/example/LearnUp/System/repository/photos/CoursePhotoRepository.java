package com.example.LearnUp.System.repository.photos;

import com.example.LearnUp.System.entity.CourseEntity.CoursePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursePhotoRepository extends JpaRepository<CoursePhoto, Long> {
        @Query("SELECT cp FROM CoursePhoto cp WHERE cp.course.id = :courseId")
        CoursePhoto findByCourseId(@Param("courseId") int courseId);
}
