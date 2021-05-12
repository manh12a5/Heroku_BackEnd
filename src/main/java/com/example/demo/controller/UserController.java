package com.example.demo.controller;

import com.example.demo.model.user.AppUser;
import com.example.demo.model.user.response.ResponseMessage;
import com.example.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin( "*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping("/profile/{id}")
    public ResponseEntity<?> showProFile(@PathVariable Long id){
        if(userService.findById(id) == null){
            return new ResponseEntity<>(new ResponseMessage("Not found"),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<AppUser> editProFile(@PathVariable Long id, @RequestBody AppUser appUser){
        appUser.setId(id);
        return new ResponseEntity<>(userService.save(appUser), HttpStatus.OK);
    }



}
