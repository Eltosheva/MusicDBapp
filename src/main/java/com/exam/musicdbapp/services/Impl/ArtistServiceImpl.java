package com.exam.musicdbapp.services.Impl;

import com.exam.musicdbapp.model.entities.ArtistEntity;
import com.exam.musicdbapp.model.enums.ArtistNameEnum;
import com.exam.musicdbapp.repositories.ArtistNameRepository;
import com.exam.musicdbapp.services.ArtistService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistNameRepository artistNameRepository;

    public ArtistServiceImpl(ArtistNameRepository artistNameRepository) {
        this.artistNameRepository = artistNameRepository;
    }

    @Override
    public void initArtistName() {
        if (artistNameRepository.count() == 0) {
            Arrays.stream(ArtistNameEnum.values())
                    .forEach(artistNameEnum -> {
                        ArtistEntity artist = new ArtistEntity(artistNameEnum,
                                artistNameEnum + "Career information");
                        artistNameRepository.save(artist);
                    });
        }
    }
}
