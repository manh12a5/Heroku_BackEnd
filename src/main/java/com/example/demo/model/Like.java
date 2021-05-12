package com.example.demo.model;

import com.example.demo.model.user.AppUser;

import javax.persistence.*;

@Entity
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean status;
    @ManyToOne
    private AppUser appUser;
    @ManyToOne
    private Song song;
    @ManyToOne
    private PlayList playList;

    public Like() {
    }

    public Like(Long id, boolean status, AppUser appUser, Song song, PlayList playList) {
        this.id = id;
        this.status = status;
        this.appUser = appUser;
        this.song = song;
        this.playList = playList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public PlayList getPlayList() {
        return playList;
    }

    public void setPlayList(PlayList playList) {
        this.playList = playList;
    }
}
