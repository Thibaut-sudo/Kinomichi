package org.example.kinomichi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class MTMEventToUser {


    @Id
    private Long id;
    private Long event_id;

    private Long user_id;




    public MTMEventToUser() {

    }

    public MTMEventToUser(Long aLong, Long id, Long id1) {
        this.id = aLong;
        this.event_id = id;
        this.user_id = id1;
    }
}





