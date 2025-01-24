package com.example.LearnUp.System.repository.CategoryRepository;

import com.example.LearnUp.System.entity.CategoryEntity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByCategoryName(String categoryName);


}
