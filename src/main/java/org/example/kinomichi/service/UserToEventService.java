package org.example.kinomichi.service;

import lombok.extern.slf4j.Slf4j;
import org.example.kinomichi.model.MTMEventToUser;
import org.example.kinomichi.model._Event;
import org.example.kinomichi.repository.UserToEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserToEventService {
    @Autowired
    private UserToEventRepository userToEventRepository;

    public void saveUserToEvent(MTMEventToUser mtmEventToUser) {
        userToEventRepository.save(mtmEventToUser);
    }


    public List<Long> getEventsByUserId(Long id) {
        return userToEventRepository.getEventsByUserId(id);
    }
}
