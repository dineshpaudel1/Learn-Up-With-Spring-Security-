package com.example.LearnUp.System.controllers;

import com.example.LearnUp.System.model.teacher.Teacher;
import com.example.LearnUp.System.service.Teacher.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TeachersController {

    private final TeacherService teacherService;

    @PostMapping("user/teacherRegister")
    public ResponseEntity<Object> registerTeacher(@RequestBody Teacher teacher) {

        System.out.println(teacher);
        return teacherService.registerTeacher(teacher);
    }

    @GetMapping("admin/viewUnApproved")
    public ResponseEntity<Object> unApprovedTeacher() {
        return teacherService.getUnApprovedTeacher();
    }

    @PutMapping("admin/approveTeacher")
    public ResponseEntity<Object> approveTeacher(@RequestBody Teacher teacher) {
        return teacherService.verifyTeacher(teacher);
    }
}
