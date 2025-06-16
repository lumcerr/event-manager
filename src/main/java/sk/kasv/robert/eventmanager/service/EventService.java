package sk.kasv.robert.eventmanager.service;

import sk.kasv.robert.eventmanager.entity.Event;
import sk.kasv.robert.eventmanager.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public List<Event> getEventsByCategory(String categoryName) {
        return eventRepository.findByCategoryName(categoryName);
    }

    public List<Event> getEventsByOrganizer(Long organizerId) {
        return eventRepository.findByOrganizerId(organizerId);
    }

    public List<Event> getEventsBetweenDates(LocalDateTime start, LocalDateTime end) {
        return eventRepository.findByStartTimeBetween(start, end);
    }

    public List<Event> getEventsByCity(String city) {
        return eventRepository.findByLocationCityIgnoreCase(city);
    }

    public Optional<Event> updateEvent(Long id, Event updatedEvent) {
        return eventRepository.findById(id).map(event -> {
            event.setEventName(updatedEvent.getEventName());
            event.setDescription(updatedEvent.getDescription());
            event.setStartTime(updatedEvent.getStartTime());
            event.setEndTime(updatedEvent.getEndTime());
            // Optionally, update relationships too:
            event.setCategory(updatedEvent.getCategory());
            event.setLocation(updatedEvent.getLocation());
            event.setOrganizer(updatedEvent.getOrganizer());
            return eventRepository.save(event);
        });
    }

    // Delete an event by ID
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
