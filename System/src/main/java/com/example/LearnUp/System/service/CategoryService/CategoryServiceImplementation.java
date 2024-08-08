package com.example.LearnUp.System.service.CategoryService;

import com.example.LearnUp.System.entity.CategoryEntity.CategoryEntity;
import com.example.LearnUp.System.model.CategoryModel.Category;
import com.example.LearnUp.System.model.Response;
import com.example.LearnUp.System.repository.CategoryRepository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Value("${project.image}")
    private String path;

    @Override
    public ResponseEntity<Object> addCategory(Category category) {
        try {
            CategoryEntity categoryEntity = CategoryEntity.builder()
                    .categoryName(category.getCategoryName()).build();
            categoryRepository.save(categoryEntity);
            return new ResponseEntity<>("Category added successfully", HttpStatus.OK);
        } catch (Exception e) {  // Catching more generic Exception
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong: " + e.getMessage());
        }
    }


}
