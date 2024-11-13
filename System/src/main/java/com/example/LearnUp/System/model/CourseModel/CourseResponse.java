package com.example.LearnUp.System.model.CourseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    private int id;
    private String courseTitle;
    private String courseDescription;
    private String category;
    private Long price;
    private Double rating;
    private String thumbnail;
    private String instructor;
    private String language;
    private String videoLink;
}
