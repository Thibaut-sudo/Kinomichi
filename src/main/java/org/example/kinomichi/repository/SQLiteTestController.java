package org.example.kinomichi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SQLiteTestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/test-connection")
    public String testConnection() {
        try {
            // Effectuer une simple requête pour tester la connexion
            String query = "SELECT * FROM _user";
            Integer result = jdbcTemplate.queryForObject(query, Integer.class);
            System.out.println(result);
            if (result != null && result == 1) {
                return "Connexion à la base de données SQLite réussie!";
            } else {
                return "Erreur de connexion à la base de données SQLite.";
            }
        } catch (Exception e) {
            return "Erreur de connexion : " + e.getMessage();
        }
    }
}
