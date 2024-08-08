package com.example.LearnUp.System.entity.CategoryEntity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category_photo")
@Builder
@Data
public class CategoryPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private CategoryEntity categoryEntity;
}
