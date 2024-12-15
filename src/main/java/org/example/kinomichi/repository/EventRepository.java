package org.example.kinomichi.repository;

import org.example.kinomichi.model._Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<_Event, Long> {
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM _Event e WHERE e.name = :name")
    public boolean existsByName(String name);

    @Query(value = "SELECT * FROM _Event e WHERE e.club_id = :id AND datetime(e.date / 1000, 'unixepoch') > CURRENT_DATE ORDER BY e.date LIMIT 1", nativeQuery = true)
    _Event findNextEventByClub(@Param("id") Long clubId);



    @Query("SELECT e FROM _Event e WHERE CAST(e.date AS DATE) < CURRENT_DATE")
    List<_Event> findUpcomingEventsForUser();
}

