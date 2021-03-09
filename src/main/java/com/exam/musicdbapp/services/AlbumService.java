package com.exam.musicdbapp.services;

import com.exam.musicdbapp.model.service.AlbumServiceModel;

import java.util.List;

public interface AlbumService {

    void addAlbum(AlbumServiceModel albumServiceModel);
    List<AlbumServiceModel> getAllAlbums();
    void deleteAlbumById(String id);
    Integer copiesCalc();
}
