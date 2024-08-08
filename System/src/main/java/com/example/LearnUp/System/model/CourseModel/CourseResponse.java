package com.example.LearnUp.System.model.CourseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    private int id;
    private String courseTitle;
    private String courseDescription;
    private String category;
    private Long price;
    private String thumbnail;
}