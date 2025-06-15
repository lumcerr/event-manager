package sk.kasv.robert.eventmanager.controller;

import sk.kasv.robert.eventmanager.entity.Event;
import sk.kasv.robert.eventmanager.service.EventService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // GET /api/events - Retrieve all events
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    // GET /api/events/{id} - Retrieve an event by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Optional<Event> eventOptional = eventService.getEventById(id);
        return eventOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/events/category?name=xyz - Retrieve events by category name
    @GetMapping("/category")
    public ResponseEntity<List<Event>> getEventsByCategory(@RequestParam String name) {
        List<Event> events = eventService.getEventsByCategory(name);
        return ResponseEntity.ok(events);
    }

    // GET /api/events/organizer?userId=123 - Retrieve events by organizer ID
    @GetMapping("/organizer")
    public ResponseEntity<List<Event>> getEventsByOrganizer(@RequestParam Long userId) {
        List<Event> events = eventService.getEventsByOrganizer(userId);
        return ResponseEntity.ok(events);
    }

    // GET /api/events/dates?start=2025-06-15T00:00:00&end=2025-06-30T23:59:59 - Retrieve events between dates
    @GetMapping("/dates")
    public ResponseEntity<List<Event>> getEventsBetweenDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        List<Event> events = eventService.getEventsBetweenDates(start, end);
        return ResponseEntity.ok(events);
    }

    // GET /api/events/city?name=xyz - Retrieve events by location city
    @GetMapping("/city")
    public ResponseEntity<List<Event>> getEventsByCity(@RequestParam String name) {
        List<Event> events = eventService.getEventsByCity(name);
        return ResponseEntity.ok(events);
    }

    // POST /api/events - Create a new event
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    // PUT /api/events/{id} - Update an existing event
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        Optional<Event> updatedEvent = eventService.updateEvent(id, event);
        return updatedEvent.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/events/{id} - Delete an event by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
