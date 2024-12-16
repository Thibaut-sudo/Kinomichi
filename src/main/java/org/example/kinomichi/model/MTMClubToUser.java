package org.example.kinomichi.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class MTMClubToUser {
    @Id
    private Long id;

    private Long club_id;

    private Long user_id;




    public MTMClubToUser() {

    }

    public MTMClubToUser(Long aLong, Long id, Long id1) {
        this.id = aLong;
        this.club_id = id;
        this.user_id = id1;

    }
}
