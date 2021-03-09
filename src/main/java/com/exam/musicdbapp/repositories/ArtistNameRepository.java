package com.exam.musicdbapp.repositories;

import com.exam.musicdbapp.model.entities.ArtistEntity;
import com.exam.musicdbapp.model.enums.ArtistNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistNameRepository extends JpaRepository<ArtistEntity, String> {
    ArtistEntity findByName(ArtistNameEnum artistNameEnum);
}
