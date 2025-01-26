package com.example.LearnUp.System.entity.CourseEntity;

import com.example.LearnUp.System.entity.CategoryEntity.CategoryEntity;
import com.example.LearnUp.System.entity.TeacherEntity.TeacherEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "courses")
@Builder
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "courseTitle", nullable = false)
    private String courseTitle;

    @Column(name = "courseDescription", nullable = false, columnDefinition = "TEXT")
    private String courseDescription;


    @Column(name = "price", nullable = false)
    private Long price;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private CategoryEntity category;

    @OneToOne
    @JoinColumn(name = "instructor" ,referencedColumnName = "id", nullable = false)
    private TeacherEntity teacher;

    @Column(name = "language", nullable = false)
    private String language;


}
