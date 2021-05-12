package com.example.demo.controller.comment;

import com.example.demo.model.comment.CommentOfSong;
import com.example.demo.model.user.AppUser;
import com.example.demo.service.SongServiceImp;
import com.example.demo.service.commentSong.ICommentSongService;
import com.example.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/commentSong")
@CrossOrigin("*")
public class CommentSongController {
    @Autowired
    ICommentSongService commentSongService;

    @Autowired
    SongServiceImp songServiceImp;

    @Autowired
    IUserService userService;



    @GetMapping("/{id}")
    public ResponseEntity<List<CommentOfSong>> list(@PathVariable Long id){
        List<CommentOfSong> commentOfSong = commentSongService.getAllBySongId(id);
        return new ResponseEntity<>(commentOfSong, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<List<CommentOfSong>>post(@RequestBody CommentOfSong commentOfSong){
        commentSongService.save(commentOfSong);
        return new ResponseEntity(commentOfSong,HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CommentOfSong>update(@RequestBody CommentOfSong commentOfSong, @PathVariable Long id){
        commentOfSong.setId(id);
        String user = String.valueOf(userService.getCurrentUser());
        String users = commentSongService.findById(id).getCreatedBy().getUsername();
        boolean us= users.equals(user);
        if (us){
            commentSongService.save(commentOfSong);
            return new ResponseEntity<>(HttpStatus.OK);
        }else
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CommentOfSong>delete(@PathVariable Long id){
        CommentOfSong commentOfSong = commentSongService.findById(id);
        AppUser appUser = userService.getCurrentUser();
        String username = appUser.getUsername();
        boolean us = username.equalsIgnoreCase(commentOfSong.getCreatedBy().getUsername());
        if (us){
            commentSongService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }

//    @GetMapping("/{id}")
//    public ResponseEntity<CommentOfSong> detail(@PathVariable Long id){
//        CommentOfSong commentOfSong = commentSongService.findById(id);
//        return  new ResponseEntity<>(commentOfSong,HttpStatus.OK);
//    }

}
