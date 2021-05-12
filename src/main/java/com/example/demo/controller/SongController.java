package com.example.demo.controller;

import com.example.demo.model.PlayList;
import com.example.demo.model.Song;
import com.example.demo.service.ISongService;
import com.example.demo.service.playlist.IPlaylistService;
import com.example.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin("*")
public class SongController {
    @Autowired
    private ISongService songService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IPlaylistService playlistService;

    @RequestMapping(value = "/songs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Song>> getAllSong() {
        List<Song> songList = songService.getAll();
        if (songList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(songList, HttpStatus.OK);
    }

    @GetMapping(value = "/song/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Song> detailSong(@PathVariable Long id) {
        Song song = songService.findById(id);
        song.setNumberOfView(song.getNumberOfView() + 1);
        songService.save(song);
        if (song == null) {
            System.out.println("Song with id : " + id + "not found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(song, HttpStatus.OK);
    }

    @PostMapping(value = "/create-song")
    public ResponseEntity<Song> createSong(@RequestBody Song song) {
        Timestamp createdTime = new Timestamp(System.currentTimeMillis());
        song.setCreatedTime(createdTime);
        song.setNumberOfView(1L);
        songService.save(song);
        return new ResponseEntity<>(song, HttpStatus.CREATED);
    }

    @PutMapping(value = "/edit-song/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Song> editSong(@PathVariable Long id, @RequestBody Song song) {
        Timestamp updatedTime = new Timestamp(System.currentTimeMillis());
        song.setUpdatedTime(updatedTime);
        song.setId(id);
        songService.save(song);
        return new ResponseEntity<>(song, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-song/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        songService.deleteSong(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Tìm kiếm theo tên bài hát
    @GetMapping(value = "/search-song", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Song>> searchNameSong(@RequestParam String name) {
        String nameSong = "%" + name + "%";
        List<Song> songList = songService.findAllByNameSong(nameSong);
        return new ResponseEntity<>(songList, HttpStatus.OK);
    }

    //Nghe nhiều
    @GetMapping(value = "/top10songsnew", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Song>> top10SongsNew() {
        List<Song> songList = songService.findAllByCreationTimeOrderByCreationTime();
        return new ResponseEntity<>(songList, HttpStatus.OK);
    }

    //Top view
    @GetMapping(value = "/top10songsview", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Song>> top10SongsView() {
        List<Song> songList = songService.findAllByNumberOfViewOrderByNumberOfView();
        return new ResponseEntity<>(songList, HttpStatus.OK);
    }

    @GetMapping("/song-playlist/{id}/user/{username}")
    public ResponseEntity<List<Song>> getListSongByIdPlayList(@PathVariable Long id, @PathVariable String username) {
        PlayList playlist = playlistService.findById(id);
        List<Song> listSong = playlist.getSongs();
        return new ResponseEntity<>(listSong, HttpStatus.OK);
    }

    @DeleteMapping("/song-playlist/{idPlaylist}/user/{username}/delete/{idSong}")
    public ResponseEntity<List<Song>> deleteSongOutPlayList(@PathVariable Long idPlaylist, @PathVariable Long idSong, @PathVariable String username) {
        PlayList playlist = playlistService.findById(idPlaylist);
        List<Song> listSong = playlist.getSongs();
        Song song = songService.findById(idSong);
        listSong.remove(song);
        playlist.setSongs(listSong);
        playlistService.save(playlist);
        return new ResponseEntity<>(listSong, HttpStatus.OK);
    }

    // lấy tất cả bài hát của user
    @GetMapping(value = "/song-user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Song>> getAllOfUserCurrent(@PathVariable Long id) {
        List<Song> songList = songService.getAllByCreateById(id);
        if (songList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(songList, HttpStatus.OK);
    }

}


