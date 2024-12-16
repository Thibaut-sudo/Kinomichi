package org.example.kinomichi.repository;

import org.example.kinomichi.model.MTMEventToUser;
import org.example.kinomichi.model._Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserToEventRepository extends JpaRepository<MTMEventToUser, Long> {
    @Query("SELECT event_id from MTMEventToUser  WHERE user_id = :id")
    List<Long> getEventsByUserId(@Param("id") Long id);


}
