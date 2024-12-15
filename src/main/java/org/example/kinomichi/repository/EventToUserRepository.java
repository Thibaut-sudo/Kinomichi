package org.example.kinomichi.repository;

import org.example.kinomichi.model.MTMEventToTUser;
import org.example.kinomichi.model._Club;
import org.example.kinomichi.model._Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventToUserRepository extends JpaRepository<MTMEventToTUser, Long> {

    @Query("SELECT e FROM _Event e WHERE e.id IN (SELECT event_id FROM MTMEventToTUser WHERE :Id = user_id)")
    public List<_Club> getClubsByUser(Long Id);

}