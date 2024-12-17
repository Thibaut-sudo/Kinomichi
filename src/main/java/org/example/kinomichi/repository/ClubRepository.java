package org.example.kinomichi.repository;

import org.example.kinomichi.model._Club;
import org.example.kinomichi.model._Event;
import org.example.kinomichi.model._User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClubRepository extends JpaRepository<_Club, Long> {

        @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM _Club c WHERE c.name = :name")
        boolean existsByName(String name);

        @Query("SELECT c FROM _Club c WHERE c.id IN (SELECT club_id FROM MTMClubToUser WHERE :clubId = user_id)")
        List<_Event> getEventsByClubId(Long clubId);

        //save club

}
