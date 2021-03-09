package com.exam.musicdbapp.model.entities;

import com.exam.musicdbapp.model.enums.AlbumGenreEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "album")
public class AlbumEntity extends BaseEntity{

    @Column(name = "name")
    private String name;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(name = "copies")
    private Integer copies;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "released_date")
    private LocalDate releasedDate;
    @Column(name = "producer")
    private String producer;
    @Enumerated(EnumType.STRING)
    private AlbumGenreEnum genre;
    @OneToOne
    private ArtistEntity artist;
    @OneToOne
    private UserEntity addedFrom;

    public AlbumEntity() {
    }

    public String getName() {
        return name;
    }

    public AlbumEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AlbumEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AlbumEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getCopies() {
        return copies;
    }

    public AlbumEntity setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AlbumEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDate getReleasedDate() {
        return releasedDate;
    }

    public AlbumEntity setReleasedDate(LocalDate releasedDate) {
        this.releasedDate = releasedDate;
        return this;
    }

    public String getProducer() {
        return producer;
    }

    public AlbumEntity setProducer(String producer) {
        this.producer = producer;
        return this;
    }

    public AlbumGenreEnum getGenre() {
        return genre;
    }

    public AlbumEntity setGenre(AlbumGenreEnum genre) {
        this.genre = genre;
        return this;
    }

    public ArtistEntity getArtist() {
        return artist;
    }

    public AlbumEntity setArtist(ArtistEntity artist) {
        this.artist = artist;
        return this;
    }

    public UserEntity getAddedFrom() {
        return addedFrom;
    }

    public AlbumEntity setAddedFrom(UserEntity addedFrom) {
        this.addedFrom = addedFrom;
        return this;
    }

    @Override
    public String toString() {
        return "AlbumEntity{" +
                "name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                ", copies=" + copies +
                ", price=" + price +
                ", releasedDate=" + releasedDate +
                ", producer='" + producer + '\'' +
                ", genre=" + genre +
                ", artist=" + artist +
                ", addedFrom=" + addedFrom +
                '}';
    }
}
