package org.example.kinomichi.repository;

import org.example.kinomichi.model.MTMEventToUser;
import org.example.kinomichi.model._Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserToEventRepository extends JpaRepository<MTMEventToUser, Long> {
    @Query("SELECT event_id from MTMEventToUser  WHERE user_id = :id")
    List<Long> getEventsByUserId(@Param("id") Long id);

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM MTMEventToUser e WHERE e.user_id = :id AND e.event_id = :id1")
    Boolean isLinked(Long id, Long id1);

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM MTMEventToUser e WHERE e.user_id = :id AND e.event_id = :id1 AND e.isPayed   = true")
    Boolean isPayed(Long id, Long id1);

    @Modifying
    @Query("UPDATE MTMEventToUser e SET e.isPayed = true WHERE e.user_id = :userId AND e.event_id = :eventId")
    void pay(@Param("userId") Long userId, @Param("eventId") Long eventId);

}
