package com.exam.musicdbapp.model.service;

import com.exam.musicdbapp.model.enums.AlbumGenreEnum;
import com.exam.musicdbapp.model.enums.ArtistNameEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AlbumServiceModel {
    private String name;
    private String id;
    private String imageUrl;
    private String description;
    private Integer copies;
    private BigDecimal price;
    private LocalDate releasedDate;
    private String producer;
    private AlbumGenreEnum genre;
    private ArtistNameEnum artist;
    private UserServiceModel addedFrom;

    public AlbumServiceModel() {
    }

    public String getName() {
        return name;
    }

    public AlbumServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getId() {
        return id;
    }

    public AlbumServiceModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AlbumServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AlbumServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getCopies() {
        return copies;
    }

    public AlbumServiceModel setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AlbumServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDate getReleasedDate() {
        return releasedDate;
    }

    public AlbumServiceModel setReleasedDate(LocalDate releasedDate) {
        this.releasedDate = releasedDate;
        return this;
    }

    public String getProducer() {
        return producer;
    }

    public AlbumServiceModel setProducer(String producer) {
        this.producer = producer;
        return this;
    }

    public AlbumGenreEnum getGenre() {
        return genre;
    }

    public AlbumServiceModel setGenre(AlbumGenreEnum genre) {
        this.genre = genre;
        return this;
    }

    public ArtistNameEnum getArtist() {
        return artist;
    }

    public AlbumServiceModel setArtist(ArtistNameEnum artist) {
        this.artist = artist;
        return this;
    }

    public UserServiceModel getAddedFrom() {
        return addedFrom;
    }

    public AlbumServiceModel setAddedFrom(UserServiceModel addedFrom) {
        this.addedFrom = addedFrom;
        return this;
    }
}
