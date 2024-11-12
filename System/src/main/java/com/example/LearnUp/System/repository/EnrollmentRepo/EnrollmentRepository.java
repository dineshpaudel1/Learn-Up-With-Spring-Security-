package com.example.LearnUp.System.repository.EnrollmentRepo;

import com.example.LearnUp.System.entity.EnrollmentEntity.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, Long> {

    // Find enrollment by username and course title
    Optional<EnrollmentEntity> findByUsernameAndCourseTitle(String username, String courseTitle);

    // Find all enrollments by userId
    List<EnrollmentEntity> findByUserId(Long userId);  // Added method to fetch by userId
}
