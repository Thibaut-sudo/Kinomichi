package org.example.kinomichi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class _Club {
    @Id

    private Long id= UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;

    private String name;

    @ManyToMany(mappedBy = "clubs")
    private Set<_User> members = new HashSet<>();

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private Set<_Event> events = new HashSet<>();
}
