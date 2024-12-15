package org.example.kinomichi.service;

import lombok.extern.slf4j.Slf4j;

import org.example.kinomichi.model._Club;
import org.example.kinomichi.model._User;
import org.example.kinomichi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public void saveUser(_User user) {


        userRepository.save(user);
    }
    public List<_User> getAllUsers() {
        return userRepository.findAll();
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean authenticate(String email) {
        return userRepository.existsByEmail(email);

    }

    public boolean isAdmin(String email) {
        return userRepository.isAdmin(email);
    }


    public _User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
