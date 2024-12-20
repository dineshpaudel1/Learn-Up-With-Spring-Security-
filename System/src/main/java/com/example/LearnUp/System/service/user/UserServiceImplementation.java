package com.example.LearnUp.System.service.user;

import com.example.LearnUp.System.config.PasswordEncoder;
import com.example.LearnUp.System.entity.UserEntity.PhotosEntity;
import com.example.LearnUp.System.entity.UserEntity.Roles;
import com.example.LearnUp.System.entity.UserEntity.UserEntity;
import com.example.LearnUp.System.model.Response;
import com.example.LearnUp.System.model.user.Password;
import com.example.LearnUp.System.model.user.UserInfo;
import com.example.LearnUp.System.repository.photos.PhotosRepository;
import com.example.LearnUp.System.repository.user.UsersRepository;
import lombok.RequiredArgsConstructor;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    @Value("${project.image}")
    private String path;

    private final UsersRepository usersRepository;

    private final PhotosRepository photosRepository;

    @Override
    public ResponseEntity<Object> addPhoto(MultipartFile file, Long id) {
        try {
            Optional<PhotosEntity> photosEntityOptional = photosRepository.findByUserId(id);
            if(photosEntityOptional.isPresent()){
                throw new IllegalArgumentException("Photo already exists");
            }

            String originalFileName = file.getOriginalFilename();
            String extension = "";
            int lastDotIndex = originalFileName.lastIndexOf('.');
            if (lastDotIndex != -1) {
                extension = originalFileName.substring(lastDotIndex);
            }

            String fileName = "user" + UUID.randomUUID() + extension;
            String filePath = path + File.separator + fileName;

            File f = new File(path);
            if (!f.exists()) {
                f.mkdir();
            }
            Files.copy(file.getInputStream(), Paths.get(filePath));

            Optional<UserEntity> userEntityOpt = usersRepository.findById(id);
            UserEntity userEntity = userEntityOpt.get();
            PhotosEntity photosEntity = PhotosEntity.builder()
                    .name("/api/photo?fileName=" + fileName)
                    .userEntity(userEntity)
                    .build();
            photosRepository.save(photosEntity);
            return new ResponseEntity<>(new Response("Photo has been added successfully"), HttpStatus.OK);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<Object> updatePhoto(MultipartFile file, Long id) {
        try {
            Optional<PhotosEntity> photosEntityOptional = photosRepository.findByUserId(id);
            if(photosEntityOptional.isPresent()){
                String originalFileName = file.getOriginalFilename();
                String extension = "";
                int lastDotIndex = originalFileName.lastIndexOf('.');
                if (lastDotIndex != -1) {
                    extension = originalFileName.substring(lastDotIndex);
                }

                String fileName = "user" + UUID.randomUUID() + extension;
                String filePath = path + File.separator + fileName;

                File f = new File(path);
                if (!f.exists()) {
                    f.mkdir();
                }
                Files.copy(file.getInputStream(), Paths.get(filePath));

                Optional<UserEntity> userEntityOpt = usersRepository.findById(id);
                UserEntity userEntity = userEntityOpt.get();
                PhotosEntity photosEntity = photosEntityOptional.get();
                photosEntity.setName("/api/photo?fileName=" + fileName);
                photosRepository.save(photosEntity);
                return new ResponseEntity<>(new Response("Photo has been updated successfully"), HttpStatus.OK);
            }else{
                throw new IllegalArgumentException("Photo not found ");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<?> returnPhoto(String fileName) {
        String filePath = path + File.separator+fileName;
        Logger.getAnonymousLogger().log(Level.FINE,filePath);
        try{
            InputStream inputStream = new FileInputStream(filePath);
            byte[] out = IOUtils.toByteArray(inputStream);
            HttpHeaders responseHeaders=new HttpHeaders();
            responseHeaders.set("charset","utf-8");
            responseHeaders.setContentType(MediaType.IMAGE_JPEG);
            return  new ResponseEntity<>(out,responseHeaders,HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Response.builder().message(e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<Object> userInfo(Long id) {
        Optional<UserInfo> userInfoOptional = usersRepository.findUserById(id);

        if (userInfoOptional.isPresent()) {
            UserInfo userInfo = userInfoOptional.get();

            // Check if photo is available, if not set it to a default value (null or placeholder URL)
            if (userInfo.getName() == null) {
                userInfo.setName(null); // or set a default URL, e.g., "https://example.com/default-photo.png"
            }

            System.out.println(userInfo);
            return new ResponseEntity<>(userInfo, HttpStatus.OK);
        } else {
            String errorMessage = "User not found";
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> updateInfo(UserInfo userInfo) {
        Optional<UserEntity> userInfoOptional = usersRepository.findById(userInfo.getId());
        UserEntity userEntity = userInfoOptional.get();
        userEntity.setFullName(userInfo.getFullName());
        userEntity.setContact(userInfo.getContact());
        userEntity.setEmail(userInfo.getEmail());
        usersRepository.save(userEntity);
        return new ResponseEntity<>(new Response("Updated Successfully"), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Object> updatePassword(Password password) {
        UserEntity userEntity = usersRepository.findById(password.getId()).get();
        if(new PasswordEncoder().matches(password.getPassword(), userEntity.getPassword())){
            throw new BadCredentialsException("Please enter correct password");
        }else{
            userEntity.setPassword(new PasswordEncoder().encodePassword(password.getNewPassword()));
            usersRepository.save(userEntity);
            return new ResponseEntity<>(new Response("Password has been updated successfully"),HttpStatus.OK);
        }
    }
    @Override
    public ResponseEntity<Object> getAllUsers() {
        List<UserEntity> users = usersRepository.findAll();
        if (!users.isEmpty()) {
            List<UserInfo> userInfos = users.stream()
                    .map(user -> {
                        UserInfo dto = new UserInfo();
                        dto.setId(user.getId());
                        dto.setFullName(user.getFullName());
                        dto.setUsername(user.getUsername());
                        dto.setEmail(user.getEmail());
                        dto.setContact(user.getContact());
                        dto.setName(user.getPhoto() != null ? user.getPhoto().getName() : null);
                        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
                            Roles firstRole = user.getRoles().iterator().next(); // Get the first role
                            dto.setRoles(firstRole);
                        } else {
                            dto.setRoles(null); // Handle cases where no roles are present
                        }
                        return dto;
                    })
                    .collect(Collectors.toList());
            return new ResponseEntity<>(userInfos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Response("No users found"), HttpStatus.NOT_FOUND);
        }
    }
}
