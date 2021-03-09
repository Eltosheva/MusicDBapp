package com.exam.musicdbapp.model.entities;

import com.exam.musicdbapp.model.enums.ArtistNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "artists")
public class ArtistEntity extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private ArtistNameEnum name;
    @Column(name = "career_information", columnDefinition = "TEXT")
    private String careerInformation;

    public ArtistEntity() {
    }

    public ArtistEntity(ArtistNameEnum name, String careerInformation) {
        this.name = name;
        this.careerInformation= careerInformation;
    }


    public ArtistNameEnum getName() {
        return name;
    }

    public ArtistEntity setName(ArtistNameEnum name) {
        this.name = name;
        return this;
    }

    public String getCareerInformation() {
        return careerInformation;
    }

    public ArtistEntity setCareerInformation(String careerInformation) {
        this.careerInformation = careerInformation;
        return this;
    }
}
