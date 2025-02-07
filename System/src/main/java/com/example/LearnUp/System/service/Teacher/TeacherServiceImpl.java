package com.example.LearnUp.System.service.Teacher;

import com.example.LearnUp.System.entity.TeacherEntity.TeacherEntity;
import com.example.LearnUp.System.entity.UserEntity.Roles;
import com.example.LearnUp.System.entity.UserEntity.UserEntity;
import com.example.LearnUp.System.entity.UserEntity.UserRoles;
import com.example.LearnUp.System.model.Response;
import com.example.LearnUp.System.model.teacher.Teacher;
import com.example.LearnUp.System.model.teacher.TeacherInfo;
import com.example.LearnUp.System.repository.Teachers.TeachersRepository;
import com.example.LearnUp.System.repository.user.RolesRepository;
import com.example.LearnUp.System.repository.user.UserRolesRepository;
import com.example.LearnUp.System.repository.user.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService{

    private final TeachersRepository teachersRepository;
    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final UserRolesRepository userRolesRepository;

    @Override
    public ResponseEntity<Object> registerTeacher(Teacher teacher) {

        UserEntity userEntity = usersRepository.findById(teacher.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + teacher.getId()));


        TeacherEntity teacherEntity = TeacherEntity.builder()
                .highestQualification(teacher.getHighestQualification())
                .speciality(teacher.getSpeciality())
                .bio(teacher.getBio())
                .user(userEntity)
                .status(false)
                .build();

        teachersRepository.save(teacherEntity);
        return new ResponseEntity<>(new Response("Registered Successfully! Pending to be approved"),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getUnApprovedTeacher() {

        List<TeacherInfo> info = teachersRepository.findTeacherByStatus(false);
        System.out.println(info);
        return new ResponseEntity<>(info, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Object> verifyTeacher(Teacher teacher) {

        TeacherEntity teacherEntity = teachersRepository.findById(teacher.getId()).get();
        long id = teacherEntity.getUser().getId();
        teacherEntity.setStatus(true);

        Roles roles = rolesRepository.findByName("ROLE_TEACHER").get();
        UserRoles userRoles = userRolesRepository.findUserRoleById(id);
        userRoles.setRoles(roles);

        userRolesRepository.save(userRoles);
        teachersRepository.save(teacherEntity);
        return new ResponseEntity<>(new Response("Teacher has been verified"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getTeacherInfo() {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        TeacherInfo info = teachersRepository.findTeacherById(user.getId());

        if (info == null) {
            return new ResponseEntity<>(new Response("User with "+ user.getId()+ " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(info, HttpStatus.OK);
    }
}
