package com.example.demo.model.comment;

import com.example.demo.model.PlayList;
import com.example.demo.model.user.AppUser;

import javax.persistence.*;

@Entity
public class CommentOfPlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne
    private AppUser createdBy;
    @ManyToOne
    private PlayList playList;

    public CommentOfPlayList() {
    }

    public CommentOfPlayList(Long id, String content, AppUser createdBy, PlayList playList) {
        this.id = id;
        this.content = content;
        this.createdBy = createdBy;
        this.playList = playList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AppUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(AppUser createdBy) {
        this.createdBy = createdBy;
    }

    public PlayList getPlayList() {
        return playList;
    }

    public void setPlayList(PlayList playList) {
        this.playList = playList;
    }
}

