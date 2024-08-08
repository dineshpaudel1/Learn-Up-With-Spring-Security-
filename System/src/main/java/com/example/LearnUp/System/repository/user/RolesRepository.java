package com.example.LearnUp.System.repository.user;

import com.example.LearnUp.System.entity.UserEntity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles,Integer> {
    Optional<Roles> findByName(String name);
}
