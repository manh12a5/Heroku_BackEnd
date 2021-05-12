package com.example.demo.controller;

import com.example.demo.model.PlayList;
import com.example.demo.model.user.AppUser;
import com.example.demo.service.playlist.PlaylistService;
import com.example.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<List<PlayList>> showAll() {
        List<PlayList> playLists = playlistService.findAll();
        if (playLists.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(playLists, HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<PlayList> createPlaylist(@RequestBody PlayList playList) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        playList.setTimeCreate(timestamp);
        playlistService.save(playList);
        return new ResponseEntity<>(playList, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<PlayList> editPlaylist(@PathVariable Long id, @RequestBody PlayList playList) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        playList.setTimeUpdate(timestamp);
        playList.setId(id);
        if (playList.getId() == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            playlistService.save(playList);
            return new ResponseEntity<>(playList, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PlayList> deletePlaylist(@PathVariable Long id) {
        playlistService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //CRUD Theo User
    @GetMapping("/user/{username}")
    public ResponseEntity<List<PlayList>> findAllByUserUsername(@PathVariable String username) {
        List<PlayList> playlists = playlistService.findAllByAppUserUsername(username);
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }

    @GetMapping("/user/{username}/{id}")
    public ResponseEntity<PlayList> getPlayListById(@PathVariable Long id, @PathVariable String username) {
        PlayList playList = playlistService.findById(id);
        if (playList == null) {
            System.out.println("Playlist with id : " + id + "not found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(playList, HttpStatus.OK);
        }
    }

    @PostMapping("/user/create/{username}")
    public ResponseEntity<PlayList> createNewPlayListByUser(@RequestBody PlayList playList, @PathVariable String username) {
        AppUser appUser = userService.findUserByUserName(username);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        playList.setTimeCreate(timestamp);
        playList.setUser(appUser);
        return new ResponseEntity<>(playlistService.save(playList), HttpStatus.CREATED);
    }

    @GetMapping( "/{id}")
    public ResponseEntity<PlayList> detail(@PathVariable Long id) {
        PlayList playList = playlistService.findById(id);
        return new ResponseEntity<>(playList, HttpStatus.OK);
    }

    @DeleteMapping("/user/delete/{username}/{id}")
    public ResponseEntity<PlayList> deletePlayListByUser(@PathVariable Long id, @PathVariable String username) {
        playlistService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/user/edit/{username}/{id}")
    public ResponseEntity<PlayList> updatePlayListByUser(@PathVariable String username, @RequestBody PlayList playList, @PathVariable Long id) {
        if (playlistService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            AppUser appUser = userService.findUserByUserName(username);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            playList.setTimeUpdate(timestamp);
            playList.setUser(appUser);
            playlistService.save(playList);
            return new ResponseEntity<>(playList, HttpStatus.OK);
        }
    }

    @GetMapping("/user/{idPlaylist}/songs/{idSong}")
    public ResponseEntity<PlayList> addSongToPlaylist(@PathVariable("idPlaylist") Long idPlaylist, @PathVariable("idSong") Long idSong) {
        return new ResponseEntity<>(playlistService.addSongToPlaylist(idSong, idPlaylist), HttpStatus.OK);
    }

    //Tìm kiếm playlist
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlayList>> searchNameSong(@RequestParam String name) {
        String namePlaylist = "%" + name + "%";
        List<PlayList> songList = playlistService.findAllByName(namePlaylist);
        return new ResponseEntity<>(songList, HttpStatus.OK);
    }

    //playlist moi nhat
    @GetMapping(value = "/top10newplaylist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlayList>> top10PlaylistNew() {
        List<PlayList> playLists = playlistService.findAllByCreationTimeOrderByCreationTime();
        return new ResponseEntity<>(playLists, HttpStatus.OK);
    }


}
