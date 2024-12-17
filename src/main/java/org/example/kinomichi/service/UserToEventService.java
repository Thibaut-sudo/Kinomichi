package org.example.kinomichi.service;

import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.example.kinomichi.model.MTMEventToUser;
import org.example.kinomichi.model._Event;
import org.example.kinomichi.repository.UserToEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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



    public Boolean isLinked(Long id, Long id1) {
        return userToEventRepository.isLinked(id, id1);
    }

    public void joinEvent(Long id, Long id1) {
        MTMEventToUser mtmEventToUser = new MTMEventToUser();
        mtmEventToUser.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        mtmEventToUser.setEvent_id(id);
        mtmEventToUser.setUser_id(id1);
        userToEventRepository.save(mtmEventToUser);
    }

    public Boolean isPayed(Long id, Long id1) {
        return userToEventRepository.isPayed(id, id1);
    }
    @Transactional
    public void pay(Long id, Long id1) {
        userToEventRepository.pay(id, id1);
        log.error("payed");
    }
}
