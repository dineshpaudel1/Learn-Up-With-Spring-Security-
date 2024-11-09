package com.example.LearnUp.System.repository.EnrollmentRepo;

import com.example.LearnUp.System.entity.EnrollmentEntity.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, Long> {
}
