package org.example.kinomichi.service;

import lombok.extern.slf4j.Slf4j;
import org.example.kinomichi.model._Club;
import org.example.kinomichi.repository.ClubRepository;
import org.example.kinomichi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClubService {
    @Autowired
    private ClubRepository clubRepository;

    public void saveClub(_Club club) {
        clubRepository.save(club);
    }

    public boolean existsByName(String name) {
        return clubRepository.existsByName(name);
    }

    public List<_Club> getAllClub() {
        return clubRepository.findAll();
    }

    public _Club getClubById(Long clubId) {
        return clubRepository.findById(clubId).orElse(null);
    }
}
