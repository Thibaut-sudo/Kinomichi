package org.example.kinomichi.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class MTMClubToUser {
    @Id
    private Long club_id;

    private Long user_id;




}
