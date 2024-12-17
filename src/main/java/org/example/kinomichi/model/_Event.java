package org.example.kinomichi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Setter
@Getter
public class _Event {
    @Id
    private Long id ;


    private String name;
    private String description;
    // Getters et setters

    @DateTimeFormat(pattern = "yyyy-MM-dd")  // Assurez-vous que le format est bien spécifié
    private LocalDate date;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private long club_id;
    private int Price;


    public void setClub(_Club selectedClub) {
        this.club_id = selectedClub.getId();
    }
}

