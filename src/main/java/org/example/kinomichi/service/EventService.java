package org.example.kinomichi.service;

import lombok.extern.slf4j.Slf4j;
import org.example.kinomichi.model._Club;
import org.example.kinomichi.model._Event;
import org.example.kinomichi.repository.ClubRepository;
import org.example.kinomichi.repository.EventRepository;
import org.example.kinomichi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public void saveEvent(_Event event) {
        eventRepository.save(event);
    }

    public boolean existsByName(String name) {
        return eventRepository.existsByName(name);
    }

    public List<_Event> getAllEvent() {
        return eventRepository.findAll();
    }

    public List<_Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public _Event getNextEventByClub(Long id) {
        return eventRepository.findNextEventByClub(id);
    }

    public List<_Event> getUpcomingEventsForUser() {
        return eventRepository.findUpcomingEventsForUser();
    }
}
