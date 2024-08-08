package com.example.LearnUp.System.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Password {
    private Long id;
    private String password;
    private String newPassword;
}
