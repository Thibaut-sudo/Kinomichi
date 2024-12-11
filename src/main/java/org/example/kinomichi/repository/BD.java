package org.example.kinomichi.repository;

import org.example.kinomichi.model._User;
import org.example.kinomichi.util.SaveUserInJson;

import java.io.IOException;
import java.util.List;

public class BD implements Repo {


    @Override
    public void insertUser(_User user) {
        SaveUserInJson.saveUserInJson(user);
    }

    @Override
    public void deleteUser(_User user) {

    }

    @Override
    public void deleteAllUsers() {
        SaveUserInJson.deleteAllUsers();
    }

    @Override
    public void updateUser(_User user) {

    }

    @Override
    public _User getUser(_User user) {
        return null;
    }

    @Override
    public List<_User> getAllUsers() throws IOException {
        return SaveUserInJson.getAllUsers();

    }
}
