package com.example.LearnUp.System.service.Teacher;

import com.example.LearnUp.System.model.teacher.Teacher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TeacherService {

    ResponseEntity<Object> registerTeacher(Teacher teacher);

    ResponseEntity<Object> getUnApprovedTeacher();

    ResponseEntity<Object> verifyTeacher(Teacher teacher);
}
