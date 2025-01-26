package com.example.LearnUp.System.entity.TeacherEntity;

import com.example.LearnUp.System.entity.UserEntity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teachers")
public class TeacherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "highest_qualification", nullable = false)
    private String highestQualification;

    @Column(name = "speciality", nullable = false)
    private String speciality;

    @Column(name = "bio", length = 500)
    private String bio;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserEntity user;

    @Column(name = "status")
    private Boolean status;


}
