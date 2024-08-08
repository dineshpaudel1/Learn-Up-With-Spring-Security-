package com.example.LearnUp.System.repository.user;

import com.example.LearnUp.System.entity.UserEntity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles,Integer> {
    @Query("SELECT u FROM UserRoles u WHERE u.userEntity.id = :id")
    UserRoles findUserRoleById(@Param("id") Long id);
}
