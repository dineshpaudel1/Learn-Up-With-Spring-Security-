package com.example.LearnUp.System.repository.CategoryRepository;

import com.example.LearnUp.System.entity.CategoryEntity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

}
