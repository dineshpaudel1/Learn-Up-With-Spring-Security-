package com.example.LearnUp.System.model.CourseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private String courseTitle;
    private String courseDescription;
    private String category;
    private Long price;

}