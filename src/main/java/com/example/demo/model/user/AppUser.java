package com.example.demo.model.user;

import com.example.demo.model.PlayList;
import net.minidev.json.annotate.JsonIgnore;

import javax.validation.constraints.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Size(min = 6, max = 20)
    private String username;

    @NotBlank
    @NotNull
    @Size(min = 6, max = 20)
    private String password;
    @NotBlank
    private String fullName;
    @Size(max = 60)
    private String address;
    @Email
    @NotNull
    private String email;

    private String phone;
    private String avatar;
    @ManyToMany
//    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    @OneToMany
    private List<PlayList> playList;

    public AppUser() {
    }

    public AppUser(@NotBlank @Size(min = 6, max = 20) String username, @NotBlank @Size(min = 8, max = 30) String password) {
        this.username = username;
        this.password = password;
    }

    public AppUser(Long id, @NotBlank @Size(min = 6, max = 20) String username, @NotBlank @Size(min = 8, max = 30) String password, @NotBlank String fullName, @Size(max = 60) String address, @Email String email, String phone, String avatar, Set<Role> roles, List<PlayList> playList) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.roles = roles;
        this.playList = playList;
    }

    public AppUser(@NotBlank @Size(min = 6, max = 20) String username, @NotBlank @Size(min = 8, max = 30) String password, String phone) {
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    public AppUser(@NotBlank @Size(min = 6, max = 20) String username, @NotBlank @Size(min = 8, max = 30) String password, @NotBlank String fullName, @Size(max = 60) String address, @Email String email, String phone) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<PlayList> getPlayList() {
        return playList;
    }

    public void setPlayList(List<PlayList> playList) {
        this.playList = playList;
    }
}
