package com.example.LearnUp.System.service.EnrollmentService;

import com.example.LearnUp.System.entity.EnrollmentEntity.EnrollmentEntity;
import com.example.LearnUp.System.repository.EnrollmentRepo.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public List<EnrollmentEntity> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public EnrollmentEntity saveEnrollment(EnrollmentEntity enrollment) {
        return enrollmentRepository.save(enrollment);
    }
}
