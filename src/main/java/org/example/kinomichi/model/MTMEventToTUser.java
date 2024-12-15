package org.example.kinomichi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class MTMEventToTUser {


    @Id
    private Long event_id;

    private Long user_id;


}





