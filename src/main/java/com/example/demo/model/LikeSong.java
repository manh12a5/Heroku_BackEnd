package com.example.demo.model;

import com.example.demo.model.user.AppUser;

import javax.persistence.*;

@Entity
public class LikeSong {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AppUser appUser;

    @ManyToOne
    private Song song;

    public LikeSong() {
    }

    public LikeSong(Long id, AppUser appUser, Song song) {
        this.id = id;
        this.appUser = appUser;
        this.song = song;
    }

    public LikeSong(AppUser appUser, Song song) {
        this.appUser = appUser;
        this.song = song;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getUser() {
        return appUser;
    }

    public void setUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
