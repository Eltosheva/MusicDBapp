package com.exam.musicdbapp.services.Impl;

import com.exam.musicdbapp.model.entities.AlbumEntity;
import com.exam.musicdbapp.model.entities.UserEntity;
import com.exam.musicdbapp.model.service.AlbumServiceModel;
import com.exam.musicdbapp.repositories.AlbumRepository;
import com.exam.musicdbapp.repositories.ArtistNameRepository;
import com.exam.musicdbapp.services.AlbumService;
import com.exam.musicdbapp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final ModelMapper modelMapper;
    private final ArtistNameRepository artistNameRepository;
    private final UserService userService;

    public AlbumServiceImpl(AlbumRepository albumRepository, ModelMapper modelMapper, ArtistNameRepository artistNameRepository, UserService userService) {
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
        this.artistNameRepository = artistNameRepository;
        this.userService = userService;
    }

    @Override
    public void addAlbum(AlbumServiceModel albumServiceModel) {
        AlbumEntity albumEntity = modelMapper.map(albumServiceModel, AlbumEntity.class);
        albumEntity.setAddedFrom(modelMapper.map(userService.getLoggedUser(), UserEntity.class));
        albumEntity.setArtist(artistNameRepository.findByName(albumServiceModel.getArtist()));
        albumEntity.setGenre(albumServiceModel.getGenre());
        albumRepository.save(albumEntity);
    }

    @Override
    public List<AlbumServiceModel> getAllAlbums() {
        List<AlbumServiceModel> albumServiceModels = new ArrayList<>();
        albumRepository
                .findAll()
                .stream()
                .filter(albumEntity -> albumEntity.getAddedFrom().getId().equals(userService.getLoggedUser().getId()))
                .forEach(albumEntity -> {
                    AlbumServiceModel albumServiceModel = new AlbumServiceModel();
                    modelMapper.map(albumEntity, albumServiceModel);
                    albumServiceModel.setArtist(albumEntity.getArtist().getName());
                    albumServiceModel.setAddedFrom(userService.getLoggedUser());
                    albumServiceModels.add(albumServiceModel);
                });
        return albumServiceModels
                .stream()
                .sorted(Comparator.comparingInt(AlbumServiceModel::getCopies).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAlbumById(String id) {
        albumRepository.deleteById(id);
    }

    @Override
    public Integer copiesCalc() {
        return albumRepository
                .findAll()
                .stream()
                .filter(albumEntity -> albumEntity.getAddedFrom().getId().equals(userService.getLoggedUser().getId()))
                .mapToInt(AlbumEntity::getCopies)
                .sum();
    }
}
