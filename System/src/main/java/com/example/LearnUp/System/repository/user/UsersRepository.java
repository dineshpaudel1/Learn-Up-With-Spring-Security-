package com.example.LearnUp.System.repository.user;

import com.example.LearnUp.System.entity.UserEntity.UserEntity;
import com.example.LearnUp.System.model.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.contact = :contact")
    Optional<UserEntity> findByContact(@Param("contact") String contact);

    Optional<UserEntity> findByUsername(String username);

    @Query("SELECT u FROM UserEntity u WHERE u.email = :email")
    Optional<UserEntity> findByEmail(String email);

    Boolean existsByUsername(String username);

    @Query("SELECT new com.example.LearnUp.System.model.user.UserInfo(u.id, u.fullName, u.username, u.email, u.contact, up.name, u.roles) " +
            "FROM UserEntity u " +
            "LEFT JOIN PhotosEntity AS up ON u.id = up.userEntity.id " +
            "WHERE u.id = :id")
    Optional<UserInfo> findUserById(@Param("id") Long id);

}
