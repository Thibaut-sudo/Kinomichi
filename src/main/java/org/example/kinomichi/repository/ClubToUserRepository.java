package org.example.kinomichi.repository;

import org.example.kinomichi.model.MTMClubToUser;
import org.example.kinomichi.model._Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ClubToUserRepository extends JpaRepository<MTMClubToUser , Long>  {

    @Query("SELECT c FROM _Club c WHERE c.id IN (SELECT club_id FROM MTMClubToUser WHERE :Id = user_id)")
    public List<_Club> getClubsByUser(Long Id);
}
