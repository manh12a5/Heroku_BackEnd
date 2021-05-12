package com.example.demo.model;

import com.example.demo.model.comment.CommentOfSong;
import com.example.demo.model.user.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameSong;
    private String description;
    private String fileMp3;
    private String fileImage;
    private String author;
    @Column(nullable = false)
    private Timestamp createdTime;
    private Timestamp updatedTime;
    private Long numberOfView;
    private String singer;
    @ManyToOne
    private AppUser CreateBy;
    @ManyToOne
    private Category category;
    @ManyToOne
    private PlayList playList;
//    @OneToMany
//    @JsonIgnore
//    private List<Like> likes;
    @OneToMany
    @JsonIgnore
    private List<CommentOfSong> comments;

    public Song() {
    }

    public Song(Long id, String nameSong, String description, String fileMp3, String fileImage, String author, Timestamp createdTime, Timestamp updatedTime, Long numberOfView, String singer, AppUser createBy, Category category, PlayList playList, List<CommentOfSong> comments) {
        this.id = id;
        this.nameSong = nameSong;
        this.description = description;
        this.fileMp3 = fileMp3;
        this.fileImage = fileImage;
        this.author = author;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.numberOfView = numberOfView;
        this.singer = singer;
        CreateBy = createBy;
        this.category = category;
        this.playList = playList;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileMp3() {
        return fileMp3;
    }

    public void setFileMp3(String fileMp3) {
        this.fileMp3 = fileMp3;
    }

    public String getFileImage() {
        return fileImage;
    }

    public void setFileImage(String fileImage) {
        this.fileImage = fileImage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Long getNumberOfView() {
        return numberOfView;
    }

    public void setNumberOfView(Long numberOfView) {
        this.numberOfView = numberOfView;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public AppUser getCreateBy() {
        return CreateBy;
    }

    public void setCreateBy(AppUser createBy) {
        CreateBy = createBy;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public PlayList getPlayList() {
        return playList;
    }

    public void setPlayList(PlayList playList) {
        this.playList = playList;
    }

//    public List<Like> getLikes() {
//        return likes;
//    }
//
//    public void setLikes(List<Like> likes) {
//        this.likes = likes;
//    }

    public List<CommentOfSong> getComments() {
        return comments;
    }

    public void setComments(List<CommentOfSong> comments) {
        this.comments = comments;
    }
}
