package org.example.kinomichi.service;

import lombok.extern.slf4j.Slf4j;
import org.example.kinomichi.model._Club;
import org.example.kinomichi.model._User;
import org.example.kinomichi.repository.ClubToUserRepository;
import org.example.kinomichi.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClubToUserService {
    @Autowired
    private ClubToUserRepository clubToUserRepository;

    public List<_Club> getClubsByUser(Long user_id) {
        return clubToUserRepository.getClubsByUser(user_id);


    }
}
