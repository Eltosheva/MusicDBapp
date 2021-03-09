package com.exam.musicdbapp.bindingModels;

import com.exam.musicdbapp.model.enums.AlbumGenreEnum;
import com.exam.musicdbapp.model.enums.ArtistNameEnum;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AddAlbumBindingModel {

    @Length(min = 3, max = 20)
    private String name;
    @Length(min = 5)
    private String imageUrl;
    @Length(min = 5)
    private String description;
    @Min(10)
    private Integer copies;
    @Positive
    private BigDecimal price;
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releasedDate;
    @NotNull
    private String producer;
    @NotNull
    private AlbumGenreEnum genre;
    @NotNull
    private ArtistNameEnum artist;

    public AddAlbumBindingModel() {
    }

    public String getName() {
        return name;
    }

    public AddAlbumBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AddAlbumBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddAlbumBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getCopies() {
        return copies;
    }

    public AddAlbumBindingModel setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AddAlbumBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDate getReleasedDate() {
        return releasedDate;
    }

    public AddAlbumBindingModel setReleasedDate(LocalDate releasedDate) {
        this.releasedDate = releasedDate;
        return this;
    }

    public String getProducer() {
        return producer;
    }

    public AddAlbumBindingModel setProducer(String producer) {
        this.producer = producer;
        return this;
    }

    public AlbumGenreEnum getGenre() {
        return genre;
    }

    public AddAlbumBindingModel setGenre(AlbumGenreEnum genre) {
        this.genre = genre;
        return this;
    }

    public ArtistNameEnum getArtist() {
        return artist;
    }

    public AddAlbumBindingModel setArtist(ArtistNameEnum artist) {
        this.artist = artist;
        return this;
    }
}
