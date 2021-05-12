package com.example.demo.controller.comment;

import com.example.demo.model.comment.CommentOfPlayList;
import com.example.demo.model.user.AppUser;
import com.example.demo.service.commentPlayList.ICommentPlayListService;
import com.example.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/commentPlaylist")
@CrossOrigin("*")
public class CommentPlayListController {
    @Autowired
    ICommentPlayListService commentPlayListService;

    @Autowired
    IUserService userService;



    @GetMapping("/{id}")
    public ResponseEntity<List<CommentOfPlayList>> list(@PathVariable Long id){
        List<CommentOfPlayList> commentOfPlayList = commentPlayListService.getAllByPlayListId(id);
        return new ResponseEntity<>(commentOfPlayList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<List<CommentOfPlayList>>post(@RequestBody CommentOfPlayList commentOfPlayList){
        return new ResponseEntity(commentPlayListService.save(commentOfPlayList),HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CommentOfPlayList>update(@RequestBody CommentOfPlayList commentOfPlayList, @PathVariable Long id){
        commentOfPlayList.setId(id);
        String user = String.valueOf(userService.getCurrentUser());
        String users = commentPlayListService.findById(id).getCreatedBy().getUsername();
        boolean us= users.equals(user);
        if (us){
            commentPlayListService.save(commentOfPlayList);
            return new ResponseEntity<>(HttpStatus.OK);
        }else
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CommentOfPlayList>delete(@PathVariable Long id){
        CommentOfPlayList commentOfPlayList = commentPlayListService.findById(id);
        AppUser appUser = userService.getCurrentUser();
        String username = appUser.getUsername();
        boolean us = username.equalsIgnoreCase(commentOfPlayList.getCreatedBy().getUsername());
        if (us){
           commentPlayListService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }
}
