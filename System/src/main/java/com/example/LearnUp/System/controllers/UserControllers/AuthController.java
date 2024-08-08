package com.example.LearnUp.System.controllers.UserControllers;

import com.example.LearnUp.System.model.auth.AuthRequest;
import com.example.LearnUp.System.model.user.Users;
import com.example.LearnUp.System.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Validated
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody Users users){
        return authService.registerUser(users);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody AuthRequest authRequest){
        return authService.login(authRequest);
    }
}
