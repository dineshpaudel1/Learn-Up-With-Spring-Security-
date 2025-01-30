package com.example.LearnUp.System.repository.CourseRepository;

import com.example.LearnUp.System.entity.CourseEntity.CourseEntity;
import com.example.LearnUp.System.model.CourseModel.CourseResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity,Long> {

    @Query("SELECT new com.example.LearnUp.System.model.CourseModel.CourseResponse(c.id, c.courseTitle, c.courseDescription, c.price, cp.coursePhotoName, c.teacher.id, c.language,cat.categoryName) " +
        "FROM CourseEntity c " +
        "INNER JOIN CoursePhoto cp ON c.id = cp.course.id " +
        "INNER JOIN CategoryEntity cat ON c.category.id = cat.id " +
        "WHERE c.teacher.id = :id")
    List<CourseResponse> teachersAllCourses(@Param("id") long id);

    @Query("SELECT new com.example.LearnUp.System.model.CourseModel.CourseResponse(c.id, c.courseTitle, c.courseDescription, c.price, cp.coursePhotoName, c.teacher.id, c.language,cat.categoryName) " +
            "FROM CourseEntity c " +
            "INNER JOIN CoursePhoto cp ON c.id = cp.course.id " +
            "INNER JOIN CategoryEntity cat ON c.category.id = cat.id ")
    List<CourseResponse> allCourses();


}
