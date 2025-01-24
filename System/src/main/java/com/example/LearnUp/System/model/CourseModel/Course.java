package com.example.LearnUp.System.model.CourseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private String courseTitle;
    private String courseDescription;
    private String category;
    private Long price;
    private String instructor;
    private String language;
}
