package org.example.kinomichi.repository;

import org.example.kinomichi.model._User;

import java.io.IOException;
import java.util.List;

public interface Repo {
    void insertUser(_User user);
    void deleteUser(_User user);
    void deleteAllUsers();

    void updateUser(_User user);

    _User getUser(_User user);
    List<_User> getAllUsers() throws IOException;



}
