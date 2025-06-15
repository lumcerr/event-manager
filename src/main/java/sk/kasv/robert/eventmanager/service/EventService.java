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

    // Constructor injection
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // Create a new event
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    // Retrieve all events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Retrieve a single event by ID
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    // Retrieve events by category name
    public List<Event> getEventsByCategory(String categoryName) {
        return eventRepository.findByCategoryName(categoryName);
    }

    // Retrieve events organized by a specific user (organizer)
    public List<Event> getEventsByOrganizer(Long organizerId) {
        return eventRepository.findByOrganizerId(organizerId);
    }

    // Retrieve events happening between two dates
    public List<Event> getEventsBetweenDates(LocalDateTime start, LocalDateTime end) {
        return eventRepository.findByStartTimeBetween(start, end);
    }

    // Retrieve events by the city in which they are held
    public List<Event> getEventsByCity(String city) {
        return eventRepository.findByLocationCityIgnoreCase(city);
    }

    // Update an existing event
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
