package com.example.demo.controller;

import com.example.demo.model.user.Role;
import com.example.demo.model.user.AppUser;
import com.example.demo.model.user.request.LoginForm;
import com.example.demo.model.user.request.SignUpForm;
import com.example.demo.model.user.UserPrinciple;
import com.example.demo.model.user.response.ResponseMessage;
import com.example.demo.security.JwtResponse;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.role.RoleService;
import com.example.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateJwtToken(authentication);
        UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();
        AppUser currentAppUser = userService.getCurrentUser();


        return ResponseEntity.ok(new JwtResponse(currentAppUser.getId(), jwt, currentAppUser.getUsername(),userDetails.getPassword(), userDetails.getEmail(),userDetails.getFullName(), userDetails.getAddress(), userDetails.getPhone(), userDetails.getAvatar(),
                userDetails.getAuthorities()
        ));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new ResponseMessage("Please check your information"), HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Existed Username"),
                    HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Existed Email"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        AppUser appUser = new AppUser(signUpRequest.getUsername(), signUpRequest.getPassword(), signUpRequest.getFullName(), signUpRequest.getAddress(), signUpRequest.getEmail(), signUpRequest.getPhone());
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleService.findByName("ROLE_ADMIN")
                            .orElseThrow(() -> new RuntimeException("Fail! -> User Role not find."));
                    roles.add(adminRole);
                    break;
                default:
                    Role userRole = roleService.findByName("ROLE_USER")
                            .orElseThrow(() -> new RuntimeException("Fail! -> User Role not find."));
                    roles.add(userRole);
            }
        });
        appUser.setRoles(roles);
        userService.save(appUser);
        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);

    }
    
}
