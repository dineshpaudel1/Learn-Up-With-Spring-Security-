package com.example.LearnUp.System.model.teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherInfo {
    private Long id;
    private String fullName;
    private String thumbnail;
    private String highestQualification;
    private String speciality;
    private String bio;

}
