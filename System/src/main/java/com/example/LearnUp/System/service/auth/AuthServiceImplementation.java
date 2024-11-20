package com.example.LearnUp.System.service.auth;

import com.example.LearnUp.System.config.PasswordEncoder;
import com.example.LearnUp.System.entity.UserEntity.PhotosEntity;
import com.example.LearnUp.System.entity.UserEntity.Roles;
import com.example.LearnUp.System.entity.UserEntity.UserEntity;
import com.example.LearnUp.System.jwt.JwtTokenUtil;
import com.example.LearnUp.System.model.auth.AuthRequest;
import com.example.LearnUp.System.model.auth.AuthResponse;
import com.example.LearnUp.System.model.Response;
import com.example.LearnUp.System.model.user.Users;
import com.example.LearnUp.System.repository.photos.PhotosRepository;
import com.example.LearnUp.System.repository.user.RolesRepository;
import com.example.LearnUp.System.repository.user.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService, UserDetailsService {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final PhotosRepository photosRepository;

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public ResponseEntity<Object> registerUser(Users users) {

        Optional<UserEntity> userEntity1;
        if(usersRepository.existsByUsername(users.getUsername())){
            throw new IllegalArgumentException("Username " + users.getUsername() + " already exists");
        }

        userEntity1 = usersRepository.findByEmail(users.getEmail());
        if(userEntity1.isPresent()){
            throw new IllegalArgumentException("Email " + users.getEmail()+ " already exist");
        }

        userEntity1 = usersRepository.findByContact(users.getContact());
        if(userEntity1.isPresent()){
            throw new IllegalArgumentException("Contact " + users.getContact()+ " already exist");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setFullName(users.getFullName());
        userEntity.setUsername(users.getUsername());
        userEntity.setEmail(users.getEmail());
        userEntity.setContact(users.getContact());
        userEntity.setPassword(new PasswordEncoder().encodePassword(users.getPassword()));

        Roles roles = rolesRepository.findByName("ROLE_USER").get();
        userEntity.setRoles(List.of(roles));

        PhotosEntity defaultPhoto = new PhotosEntity();
        defaultPhoto.setName("/api/photo?fileName=images.png");
        defaultPhoto.setUserEntity(userEntity);
        userEntity.setPhoto(defaultPhoto);

        System.out.println(userEntity);
        usersRepository.save(userEntity);
        return new ResponseEntity<>(new Response("User has been registered successfully"), HttpStatus.OK);


    }

    @Override
    public ResponseEntity<Object> login(AuthRequest authRequest) {
        Optional<UserEntity> userEntity;
        if(authRequest.getUsername().startsWith("9")){
            userEntity = usersRepository.findByContact(authRequest.getUsername());
            if(!userEntity.isPresent()){
                throw new UsernameNotFoundException("Number " + authRequest.getUsername()+ " doesn't exist! Please try with username");
            }
        }else if(authRequest.getUsername().endsWith(".com")){
            userEntity = usersRepository.findByEmail(authRequest.getUsername());
            if(!userEntity.isPresent()){
                throw new UsernameNotFoundException("Email" + " doesn't exist! Please try with username or number");
            }
        }
        else{
            userEntity = usersRepository.findByUsername(authRequest.getUsername());

            if(!userEntity.isPresent()){
                throw new UsernameNotFoundException("Username " + authRequest.getUsername()+ " doesn't exist");
            }
        }
        if(new PasswordEncoder().matches(authRequest.getPassword(), userEntity.get().getPassword())){
            throw new BadCredentialsException("Password invalid");
        }
        return ResponseEntity.ok(loginUser(userEntity.get()));


    }

    private AuthResponse loginUser(UserEntity userEntity) {
        return new AuthResponse(jwtTokenUtil.generateToken(userEntity));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional = usersRepository.findByUsername(username);
        if(userEntityOptional.isPresent()){
            return userEntityOptional.get();
        }
        throw new UsernameNotFoundException("username " + username + " not found");
    }
}
