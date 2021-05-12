package com.example.demo.controller;

import com.example.demo.model.LikeSong;
import com.example.demo.model.Song;
import com.example.demo.model.user.response.ResponseMessage;
import com.example.demo.service.SongServiceImp;
import com.example.demo.service.likeSong.LikeSongService;
import com.example.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/likeSong")
public class LikeSongController {
    @Autowired
    LikeSongService likeSongService;

    @Autowired
    UserService userService;

    @Autowired
    SongServiceImp songService;

    @GetMapping("/{id}")
    public ResponseEntity<?> likeSongModel(@PathVariable Long id) {
        if (likeSongService.findById(id) == null) {
            return new ResponseEntity<>(new ResponseMessage("Not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(likeSongService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/like")
    public ResponseEntity<?> like(@RequestBody LikeSong likeSong) {
        return new ResponseEntity<>(likeSongService.save(likeSong), HttpStatus.CREATED);
    }

    @DeleteMapping("/unlike/{sid}")
    public ResponseEntity<?> unlike(@PathVariable Long sid) {


        Song song = songService.findById(sid);

//        if (likeSong.getUser() == currentUser ) {
//            likeSongService.delete(likeSong.getId());
//            return new ResponseEntity<>(new ResponseMessage("Unlike"),HttpStatus.OK);
//        }
        if (song != null) {
            likeSongService.deleteLikeSong(sid);
            return new ResponseEntity<>(new ResponseMessage("Unlike"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseMessage("Failed"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/total/{sid}")
    public ResponseEntity<?> showTotalLikeOfSong(@PathVariable Long sid){
        Song song = songService.findById(sid);
        if (song != null){
            return new ResponseEntity<>(likeSongService.getTotalLikeOfSong(sid),HttpStatus.OK);

        }
        return new ResponseEntity<>(new ResponseMessage("Cant find this song"),HttpStatus.NOT_FOUND);
    }
}
