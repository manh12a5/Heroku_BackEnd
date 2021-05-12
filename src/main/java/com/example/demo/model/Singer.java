package com.example.demo.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameSinger;
    private Date birthDay;
    private String gender;
    private String kindOfMusic;
    private String profile;
    private String band;
    private String hitSong;
    private String extraInformation;
    @ManyToMany
    private List<Song> songs;


    public Singer() {
    }

    public Singer(Long id, String nameSinger, Date birthDay, String gender, String kindOfMusic, String profile, String band, String hitSong, String extraInformation, List<Song> songs) {
        this.id = id;
        this.nameSinger = nameSinger;
        this.birthDay = birthDay;
        this.gender = gender;
        this.kindOfMusic = kindOfMusic;
        this.profile = profile;
        this.band = band;
        this.hitSong = hitSong;
        this.extraInformation = extraInformation;
        this.songs = songs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getKindOfMusic() {
        return kindOfMusic;
    }

    public void setKindOfMusic(String kindOfMusic) {
        this.kindOfMusic = kindOfMusic;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getHitSong() {
        return hitSong;
    }

    public void setHitSong(String hitSong) {
        this.hitSong = hitSong;
    }

    public String getExtraInformation() {
        return extraInformation;
    }

    public void setExtraInformation(String extraInformation) {
        this.extraInformation = extraInformation;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
