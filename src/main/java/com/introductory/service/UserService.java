package com.introductory.service;

import com.introductory.entity.Role;
import com.introductory.entity.User;
import com.introductory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
//    //OLD METHOD
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByUsername(username);
//    }

    public boolean addUser(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        //TODO Refactor with Options
        if (userFromDb != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
//        user.setActivationCode (UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

//        if (!StringUtils.isEmpty(user.getEmail())) {
//            String message = String.format(
//                    "Hello, %s! \n" +
//                            "Please, visit next link: http://localhost:8080/activate/%s",
//                    user.getUsername(),
//                    user.getActivationCode()
//            );
//
//            mailSender.send(user.getEmail(), "Activation code", message);
//        }
//
//        return true;
//    }
//
//    public boolean activateUser(String code) {
//        User user = userRepository.findByActivationCode(code);
//        if (user == null) {
//            return false;
//        }
//
//        user.setActivationCode(null);

//        userRepository.save(user);

        return true;
    }
}
