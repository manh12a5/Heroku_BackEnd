package com.example.demo.service.user;
import com.example.demo.model.user.AppUser;
import com.example.demo.model.user.UserPrinciple;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


@Service
public class UserService implements IUserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return UserPrinciple.build(appUser);
    }
    @Override
    public AppUser save(AppUser appUser) {
       return userRepository.save(appUser);
    }
    @Override
    public AppUser findById(Long id) {
        return userRepository.findUserById(id);
    }
    @Override
    public Iterable<AppUser> findAll() {
        return userRepository.findAll();
    }
    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public AppUser findUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }


    public AppUser getCurrentUser() {
        AppUser appUser;
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        appUser = this.findUserByUserName(userName);
        return appUser;
    }


}
