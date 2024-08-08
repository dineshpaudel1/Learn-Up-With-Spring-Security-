package com.example.LearnUp.System.repository.photos;
import com.example.LearnUp.System.entity.UserEntity.PhotosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PhotosRepository extends JpaRepository<PhotosEntity,Integer> {
    @Query("SELECT p from PhotosEntity p WHERE p.userEntity.id = :userId")
    Optional<PhotosEntity> findByUserId(@Param("userId") Long userId);
}
