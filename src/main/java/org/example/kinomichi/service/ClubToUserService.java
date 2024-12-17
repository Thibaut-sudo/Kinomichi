package org.example.kinomichi.service;

import lombok.extern.slf4j.Slf4j;
import org.example.kinomichi.model.MTMClubToUser;
import org.example.kinomichi.model._Club;
import org.example.kinomichi.model._User;
import org.example.kinomichi.repository.ClubToUserRepository;
import org.example.kinomichi.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ClubToUserService {
    @Autowired
    private ClubToUserRepository clubToUserRepository;
    public void saveClubToUser(MTMClubToUser mtmClubToUser) {
        clubToUserRepository.save(mtmClubToUser);

    }
    public List<_Club> getClubsByUser(Long user_id) {
        return clubToUserRepository.getClubsByUser(user_id);


    }


    public int getnbOfUserForAClub(Long id) {
        return clubToUserRepository.getnbOfUserForAClub(id);
    }

    public void joinClub(Long id, Long id1) {
        MTMClubToUser mtmClubToUser = new MTMClubToUser();
        mtmClubToUser.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        mtmClubToUser.setClub_id(id);
        mtmClubToUser.setUser_id(id1);
        clubToUserRepository.save(mtmClubToUser);
    }

    public List<Long> getUserClubIds(Long id) {
        return clubToUserRepository.getUserClubIds(id);
    }
}
