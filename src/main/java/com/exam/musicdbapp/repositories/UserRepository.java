package com.exam.musicdbapp.repositories;

import com.exam.musicdbapp.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
  Optional<UserEntity> findByUsernameOrEmail(String username, String email);
  Optional<UserEntity> findByUsernameAndPassword(String username, String password);
}
