package com.example.LearnUp.System.repository.Teachers;

import com.example.LearnUp.System.entity.TeacherEntity.TeacherEntity;
import com.example.LearnUp.System.model.teacher.TeacherInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeachersRepository extends JpaRepository<TeacherEntity, Long> {

    @Query("SELECT new com.example.LearnUp.System.model.teacher.TeacherInfo(t.id, u.fullName, u.photo.name, t.highestQualification, t.speciality, t.bio) " +
            "FROM TeacherEntity t " +
            "INNER JOIN UserEntity u ON t.user.id = u.id " +
            "INNER JOIN PhotosEntity p ON u.id = p.userEntity.id " +
            "WHERE t.status = :status")
    List<TeacherInfo> findTeacherByStatus(@Param("status") Boolean status);

    @Query("SELECT new com.example.LearnUp.System.model.teacher.TeacherInfo(t.id, u.fullName, u.photo.name, t.highestQualification, t.speciality, t.bio) " +
            "FROM TeacherEntity t " +
            "INNER JOIN UserEntity u ON t.user.id = u.id " +
            "INNER JOIN PhotosEntity p ON u.id = p.userEntity.id " +
            "WHERE t.user.id = :id")
    TeacherInfo findTeacherById(Long id);
}
