package com.example.LearnUp.System.service.admin;

import com.example.LearnUp.System.entity.UserEntity.Roles;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface RolesService {
    ResponseEntity<Object> setRole(Long id);

    ResponseEntity<Object> setTeacherRole(Long id);

    ResponseEntity<Object> setUserRole(Long id);

    ResponseEntity<Object> addRole(Roles roles);
}
