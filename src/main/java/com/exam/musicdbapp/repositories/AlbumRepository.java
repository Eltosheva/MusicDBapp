package com.exam.musicdbapp.repositories;

import com.exam.musicdbapp.model.entities.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<AlbumEntity, String> {
}
