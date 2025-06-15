package sk.kasv.robert.eventmanager.service;

import org.springframework.stereotype.Service;
import sk.kasv.robert.eventmanager.entity.Event;
import sk.kasv.robert.eventmanager.repository.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepo;

    public EventService(EventRepository eventRepo) {
        this.eventRepo = eventRepo;
    }

    public List<Event> getAllEvents() {
        return eventRepo.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepo.findById(id);
    }

    public Event saveEvent(Event event) {
        return eventRepo.save(event);
    }

    public Event updateEvent(Long id, Event eventDetails) {
        return eventRepo.findById(id).map(event -> {
            event.setTitle(eventDetails.getTitle());
            event.setDate(eventDetails.getDate());
            event.setCategory(eventDetails.getCategory());
            event.setLocation(eventDetails.getLocation());
            return eventRepo.save(event);
        }).orElse(null);
    }

    public boolean deleteEvent(Long id) {
        if (!eventRepo.existsById(id)) {
            return false;
        }
        eventRepo.deleteById(id);
        return true;
    }
}
