package org.example.kinomichi.model;


import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;




@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class _User {

    @Id
    private Integer id;
    // Getters
    private String firstName;
    private String lastName;

    private String email;

    private String password;
    private int statut = 0;


    public _User( String firstName, String lastName, String email, String password) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }


    public void setId() {
        this.id = UUID.randomUUID().hashCode();;
    }

    //cree une methode de pour cripter le mot de passe
    public void setPassword(String password) {
        this.password = BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }



}