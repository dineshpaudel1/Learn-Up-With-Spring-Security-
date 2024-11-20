package com.example.LearnUp.System.service.auth;

import com.example.LearnUp.System.model.auth.AuthRequest;
import com.example.LearnUp.System.model.user.Users;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthService {
    ResponseEntity<Object> registerUser(Users users);

    ResponseEntity<Object> login(AuthRequest authRequest);
}
