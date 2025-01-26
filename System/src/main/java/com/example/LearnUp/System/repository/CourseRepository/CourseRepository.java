package com.example.LearnUp.System.repository.CourseRepository;

import com.example.LearnUp.System.entity.CourseEntity.CourseEntity;
import com.example.LearnUp.System.model.CourseModel.CourseResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<CourseEntity,Long> {

//    @Query("SELECT new com.example.LearnUp.System.model.CourseModel.CourseResponse(" +
//            "c.id, c.courseTitle, c.courseDescription, c.price, null, cp.coursePhotoName, c.instructor, c.language, c.category.categoryName) " +
//            "FROM CourseEntity c " +
//            "LEFT JOIN CoursePhoto cp ON c.id = cp.course.id " +
//            "LEFT JOIN CategoryEntity cat ON c.category.id = cat.id"
//    )
//    List<CourseResponse> getAllCourses();
//
//
//    List<CourseEntity> findByCategory(String category);
//
////    List<CourseEntity> findByInstructor(String instructor);
//
//    Optional<CourseEntity> findByCourseTitle(String courseTitle);

}
