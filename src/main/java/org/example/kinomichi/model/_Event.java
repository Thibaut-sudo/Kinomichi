package org.example.kinomichi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class _Event {
    @Id

    private Long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    private _Club club;

    @ManyToMany
    @JoinTable(
            name = "event_user",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<_User> participants = new HashSet<>();
}
