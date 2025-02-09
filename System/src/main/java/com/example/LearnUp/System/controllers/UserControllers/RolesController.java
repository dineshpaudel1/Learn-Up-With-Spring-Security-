package com.example.LearnUp.System.controllers.UserControllers;

import com.example.LearnUp.System.entity.UserEntity.Roles;
import com.example.LearnUp.System.service.admin.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/roles")
@RequiredArgsConstructor
public class RolesController {

    private final RolesService rolesService;


    @PutMapping("/adminRole/{id}")
    public ResponseEntity<Object> setAdminRole(@PathVariable("id") Long id){

        return rolesService.setRole(id);
    }

    @PutMapping("/teacherRole/{id}")
    public ResponseEntity<Object> setTeacher(@PathVariable("id") Long id){
        return rolesService.setTeacherRole(id);
    }

    @PutMapping("/userRole/{id}")
    public ResponseEntity<Object> setUserRole(@PathVariable("id") Long id){
        return rolesService.setUserRole(id);
    }

    @PostMapping("/addRole")
    public ResponseEntity<Object> addRole(@RequestBody Roles roles){
        return rolesService.addRole(roles);
    }
}
