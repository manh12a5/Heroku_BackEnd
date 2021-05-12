package com.example.demo.controller;

import com.example.demo.model.LikePlaylist;
import com.example.demo.model.PlayList;
import com.example.demo.model.user.response.ResponseMessage;
import com.example.demo.service.likePlaylist.LikePlaylistService;
import com.example.demo.service.playlist.PlaylistService;
import com.example.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/likePlaylist")
public class LikePlaylistController {
    @Autowired
    LikePlaylistService likePlaylistService;

    @Autowired
    PlaylistService playlistService;

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> likeSongModel(@PathVariable Long id) {
        if (likePlaylistService.findById(id) == null) {
            return new ResponseEntity<>(new ResponseMessage("Not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(likePlaylistService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/like")
    public ResponseEntity<?> like(@RequestBody LikePlaylist likePlaylist) {
        return new ResponseEntity<>(likePlaylistService.save(likePlaylist), HttpStatus.CREATED);
    }

    @DeleteMapping("/unlike/{pid}")
    public ResponseEntity<?> unlike(@PathVariable Long pid) {

        PlayList playList = playlistService.findById(pid);

        if (playList != null ) {
            likePlaylistService.deleteLikePlaylist(pid);
            return new ResponseEntity<>(new ResponseMessage("Unlike"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseMessage("Failed"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/total/{pid}")
    public ResponseEntity<?> showTotalLikeOfSong(@PathVariable Long pid){
        PlayList playList = playlistService.findById(pid);
        if ( playList != null){
            return new ResponseEntity<>(likePlaylistService.getTotalLikeOfPlaylist(pid),HttpStatus.OK);

        }
        return new ResponseEntity<>(new ResponseMessage("Cant find this playlist"),HttpStatus.NOT_FOUND);
    }



}
