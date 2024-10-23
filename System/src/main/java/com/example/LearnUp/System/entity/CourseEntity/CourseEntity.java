package com.example.LearnUp.System.entity.CourseEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="courses")
@Builder
public class CourseEntity {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private int id;
    @Column(name="courseTitle")
    private String courseTitle;
    @Column(name = "courseDescription")
    private String courseDescription;
    @Column(name = "category")
    private String category;
    @Column(name="price")
    private Long price;
    @Column(name = "thumbnail")
    private String thumbnail;
    @Column(name ="rating")
    private Double rating;
}
