package org.example.kinomichi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class _Event {
    @Id

    private Long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;

    @Getter
    private String name;
    private String description;
    // Getters et setters
    @Getter
    @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd")  // Assurez-vous que le format est bien spécifié
    private LocalDate date;
    private int club_id;


}

