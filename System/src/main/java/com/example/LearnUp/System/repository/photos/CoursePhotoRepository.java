package com.example.LearnUp.System.repository.photos;

import com.example.LearnUp.System.entity.CourseEntity.CoursePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursePhotoRepository extends JpaRepository<CoursePhoto, Long> {

}
