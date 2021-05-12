package com.example.demo.service.user;

import com.example.demo.model.user.AppUser;

public interface IUserService {
    AppUser save(AppUser appUser);

    AppUser findById(Long id);

    Iterable<AppUser> findAll();

    void remove(Long id);

    boolean existsByUsername(String username);

    boolean existsByEmail (String email);

    AppUser findUserByUserName(String username);

    AppUser getCurrentUser();
//    User findByUsername(String username);

}
