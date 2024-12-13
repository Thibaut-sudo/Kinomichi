package org.example.kinomichi.repository;

import org.example.kinomichi.model._Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<_Event, Long> {
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM _Event e WHERE e.name = :name")
    public boolean existsByName(String name);
}
