package com.example.LearnUp.System.model.CourseModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Course {
    private Long id;
    private String courseTitle;
    private String courseDescription;
    private String category;
    private Long price;
    private Long instructorId;
    private String language;
}
