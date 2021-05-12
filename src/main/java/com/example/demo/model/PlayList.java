package com.example.demo.model;

import com.example.demo.model.user.AppUser;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class PlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    private List<Song> songs;
    private String kindOfMusic;
    private Timestamp timeCreate;
    private String description;
    @ManyToOne
    private AppUser appUser;
    private Timestamp timeUpdate;
    private Long view;

    public PlayList() {
    }

    public PlayList(Long id, String name, List<Song> songs, String kindOfMusic, Timestamp timeCreate, String description, AppUser appUser, Timestamp timeUpdate, Long view) {
        this.id = id;
        this.name = name;
        this.songs = songs;
        this.kindOfMusic = kindOfMusic;
        this.timeCreate = timeCreate;
        this.description = description;
        this.appUser = appUser;
        this.timeUpdate = timeUpdate;
        this.view = view;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public String getKindOfMusic() {
        return kindOfMusic;
    }

    public void setKindOfMusic(String kindOfMusic) {
        this.kindOfMusic = kindOfMusic;
    }

    public Timestamp getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Timestamp timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AppUser getUser() {
        return appUser;
    }

    public void setUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Timestamp getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(Timestamp timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    public Long getView() {
        return view;
    }

    public void setView(Long view) {
        this.view = view;
    }
}
