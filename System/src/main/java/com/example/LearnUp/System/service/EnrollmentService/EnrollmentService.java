package com.example.LearnUp.System.service.EnrollmentService;

import com.example.LearnUp.System.entity.EnrollmentEntity.EnrollmentEntity;
import com.example.LearnUp.System.repository.EnrollmentRepo.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public List<EnrollmentEntity> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public String saveEnrollment(EnrollmentEntity enrollment) {
        // Check if the user is already enrolled in the course
        Optional<EnrollmentEntity> existingEnrollment = enrollmentRepository
                .findByUsernameAndCourseTitle(enrollment.getUsername(), enrollment.getCourseTitle());

        if (existingEnrollment.isPresent()) {
            return "User is already enrolled in this course.";
        }

        // Save the new enrollment
        enrollmentRepository.save(enrollment);
        return "Enrollment successful!";
    }
    public List<EnrollmentEntity> getUserEnrollments(Long userId) {
        return enrollmentRepository.findByUserId(userId);
    }


}
