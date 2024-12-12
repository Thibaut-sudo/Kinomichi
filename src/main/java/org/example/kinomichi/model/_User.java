package org.example.kinomichi.model;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_user")
public class _User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment behavior
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String firstName;
    private String lastName;

    @Column(nullable = false)
    private String password;

    private String statut = "user";

    // Relation Many-to-Many avec Club
    @ManyToMany
    @JoinTable(
            name = "user_club",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "club_id")
    )
    private Set<_Club> clubs = new HashSet<>();

    // Relation Many-to-Many avec Event
    @ManyToMany(mappedBy = "participants")
    private Set<_Event> events = new HashSet<>();

    // Constructeur avec hachage du mot de passe
    public _User(Long id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        setHashedPassword(password); // Hash the password when creating the user
    }

    // Méthode pour hacher le mot de passe
    public void setHashedPassword(String password) {
        this.password = BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }
}
