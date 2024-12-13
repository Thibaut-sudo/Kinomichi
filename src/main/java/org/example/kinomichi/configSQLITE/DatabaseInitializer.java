package org.example.kinomichi.configSQLITE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DatabaseInitializer {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void initializeDatabase() {
        String createUserTable = "CREATE TABLE IF NOT EXISTS _user (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "email VARCHAR(255) NOT NULL UNIQUE, " +
                "first_name VARCHAR(255), " +
                "last_name VARCHAR(255), " +
                "password VARCHAR(255) NOT NULL, " +
                "statut VARCHAR(255) DEFAULT 'user');";

        String createClubTable = "CREATE TABLE IF NOT EXISTS _club (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR(255) NOT NULL UNIQUE, " +
                "location VARCHAR(255));";

        String createEventTable = "CREATE TABLE IF NOT EXISTS _event (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR(255) NOT NULL, " +
                "date DATE, " +
                "club_id INTEGER, " +
                "FOREIGN KEY (club_id) REFERENCES _club(id));";

        jdbcTemplate.execute(createUserTable);
        jdbcTemplate.execute(createClubTable);
        jdbcTemplate.execute(createEventTable);
    }
}
