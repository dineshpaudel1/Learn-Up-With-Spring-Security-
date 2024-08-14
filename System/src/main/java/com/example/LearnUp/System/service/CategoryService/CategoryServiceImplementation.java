package com.example.LearnUp.System.service.CategoryService;

import com.example.LearnUp.System.entity.CategoryEntity.CategoryEntity;
import com.example.LearnUp.System.entity.CourseEntity.CourseEntity;
import com.example.LearnUp.System.model.CategoryModel.Category;
import com.example.LearnUp.System.model.CategoryModel.CategoryResponse;
import com.example.LearnUp.System.model.CourseModel.Course;
import com.example.LearnUp.System.model.CourseModel.CourseResponse;
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
    public ResponseEntity<Object>addCategory(Category category, MultipartFile file){
        try{
            String originalFileName = file.getOriginalFilename();
            String extension = "";
            int lastDotIndex = originalFileName.lastIndexOf('.');
            if (lastDotIndex != -1) {
                extension = originalFileName.substring(lastDotIndex);
            }

            String fileName = "category" + UUID.randomUUID() + extension;
            String filepath = path + File.separator + fileName;

            File f = new File(path);
            if(!f.exists()){
                f.mkdirs();
            }
            Files.copy(file.getInputStream(), Paths.get(filepath));

            CategoryEntity categoryEntity = CategoryEntity.builder()
                    .categoryName(category.getCategoryName())
                    .categoryPhoto("api/photo?fileName=" + fileName).build();
            categoryRepository.save(categoryEntity);
            return new ResponseEntity<>(new Response("Category has been created sucessfully"), HttpStatus.OK);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
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
    public ResponseEntity<String>updateCategory(Long categoryId, Category category, MultipartFile file){
        try{
            Optional<CategoryEntity> optionalCategory = categoryRepository.findById(categoryId);
            if(optionalCategory.isPresent()){
                CategoryEntity existingCategory = optionalCategory.get();
                existingCategory.setCategoryName(category.getCategoryName());

                if(file != null){
                    String fileName = file.getOriginalFilename();
                    String filepath = path + File.separator + fileName;

                    Files.copy(file.getInputStream(), Paths.get(filepath));
                    existingCategory.setCategoryPhoto("category/photo?fileName="+fileName);
                }
                categoryRepository.save(existingCategory);
                return new ResponseEntity<>("Course updated sucessfully",HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Course Not found: "+categoryId, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Error while updating course: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
