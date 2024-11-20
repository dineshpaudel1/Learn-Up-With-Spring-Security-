package com.example.LearnUp.System.entity.UserEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_photos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PhotosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;


    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private UserEntity userEntity;

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
