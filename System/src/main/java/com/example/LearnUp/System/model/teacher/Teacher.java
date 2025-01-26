package com.example.LearnUp.System.model.teacher;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    public Long id;
    public String highestQualification;
    public String speciality;
    public String bio;
}
