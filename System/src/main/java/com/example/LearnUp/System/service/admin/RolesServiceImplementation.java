package com.example.LearnUp.System.service.admin;
import com.example.LearnUp.System.entity.UserEntity.Roles;
import com.example.LearnUp.System.entity.UserEntity.UserRoles;
import com.example.LearnUp.System.model.Response;
import com.example.LearnUp.System.repository.user.RolesRepository;
import com.example.LearnUp.System.repository.user.UserRolesRepository;
import com.example.LearnUp.System.repository.user.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolesServiceImplementation implements RolesService {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;

    private final UserRolesRepository userRolesRepository;
    @Override
    public ResponseEntity<Object> setRole(Long id) {


        Roles roles = rolesRepository.findByName("ROLE_ADMIN").get();

        UserRoles userRoles = userRolesRepository.findUserRoleById(id);
        userRoles.setRoles(roles);
        userRolesRepository.save(userRoles);
        return new ResponseEntity<>(new Response("Role has been assigned"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> setTeacherRole(Long id) {
        Roles roles = rolesRepository.findByName("ROLE_TEACHER").get();
        UserRoles userRoles = userRolesRepository.findUserRoleById(id);
        userRoles.setRoles(roles);
        userRolesRepository.save(userRoles);
        return new ResponseEntity<>(new Response("Role has been assigned"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> setUserRole(Long id) {
        Roles roles = rolesRepository.findByName("ROLE_USER").get();
        UserRoles userRoles = userRolesRepository.findUserRoleById(id);
        userRoles.setRoles(roles);
        userRolesRepository.save(userRoles);
        return new ResponseEntity<>(new Response("Role has been assigned"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> addRole(Roles roles) {
        Roles role = Roles.builder()
                .name("ROLE_"+ roles.getName().toUpperCase())
                .build();
        rolesRepository.save(role);
        return new ResponseEntity<>(new Response("Role has been added"), HttpStatus.OK);
    }
}
