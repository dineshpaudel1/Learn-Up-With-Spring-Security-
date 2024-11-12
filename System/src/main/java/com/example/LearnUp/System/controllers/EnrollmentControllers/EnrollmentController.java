package com.example.LearnUp.System.controllers.EnrollmentControllers;

import com.example.LearnUp.System.entity.EnrollmentEntity.EnrollmentEntity;
import com.example.LearnUp.System.service.EnrollmentService.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/showEnroll")
    public List<EnrollmentEntity> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @PostMapping("/makeEnroll")
    public String createEnrollment(@RequestBody EnrollmentEntity enrollment) {
        // Call the service to save enrollment and return the result message
        return enrollmentService.saveEnrollment(enrollment);
    }

}
