package com.example.LearnUp.System.service.CategoryService;

import com.example.LearnUp.System.entity.CategoryEntity.CategoryEntity;
import com.example.LearnUp.System.model.CategoryModel.Category;
import com.example.LearnUp.System.model.CategoryModel.CategoryResponse;
import com.example.LearnUp.System.model.Response;
import com.example.LearnUp.System.repository.CategoryRepository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Value("${project.image}")
    private String path;

    @Override
    public ResponseEntity<Object>addCategory(Category category){

            CategoryEntity categoryEntity = CategoryEntity.builder()
                    .categoryName(category.getCategoryName()).build();
            categoryRepository.save(categoryEntity);
            return new ResponseEntity<>(new Response("Category has been created sucessfully"), HttpStatus.OK);

    }


    @Override
    public ResponseEntity<List<CategoryResponse>> getCategory() {
        List<CategoryEntity> categoryEntity = categoryRepository.findAll();
        List<CategoryResponse> categoryList = categoryEntity.stream()
                .map(categoryData -> {
                    CategoryResponse categoryResponse = new CategoryResponse();
                    BeanUtils.copyProperties(categoryData, categoryResponse);
                    return categoryResponse;
                }).collect(Collectors.toList());
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String>deleteCategory(Long categoryId){
        try{
            if (categoryRepository.existsById(categoryId)){
                categoryRepository.deleteById(categoryId);
                return new ResponseEntity<>("category deleted successfully", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    @Override
    public ResponseEntity<CategoryResponse> getCategoryById(Long categoryId) {
        Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findById(categoryId);

        if (categoryEntityOptional.isPresent()) {
            CategoryResponse categoryResponse = new CategoryResponse();
            BeanUtils.copyProperties(categoryEntityOptional.get(), categoryResponse);
            return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with ID: " + categoryId);
        }
    }
}
