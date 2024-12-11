package org.example.kinomichi.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.example.kinomichi.model._User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveUserInJson {

    // Ajoute un utilisateur au fichier JSON
    public static void saveUserInJson(_User user) {
        Gson gson = new Gson();
        List<_User> users = getAllUsers(); // Récupère les utilisateurs existants

        // Ajoute le nouvel utilisateur à la liste
        users.add(user);

        // Convertit la liste en JSON
        String json = gson.toJson(users);

        try (FileWriter file = new FileWriter("users.json")) {
            file.write(json);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Supprime tous les utilisateurs (vide le fichier JSON)
    public static void deleteAllUsers() {
        try (FileWriter file = new FileWriter("users.json")) {
            file.write("[]");  // Écrit un tableau vide JSON
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Récupère tous les utilisateurs à partir du fichier JSON
    public static List<_User> getAllUsers() {
        List<_User> users = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Lit le fichier JSON et le désérialise en liste d'utilisateurs
            users = mapper.readValue(new File("users.json"), new TypeReference<List<_User>>() {});
        } catch (FileNotFoundException e) {
            System.err.println("Le fichier 'users.json' est introuvable : " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erreur de lecture du fichier JSON : " + e.getMessage());
            e.printStackTrace();
        }

        return users;
    }
}
