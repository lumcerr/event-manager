package sk.kasv.robert.eventmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.kasv.robert.eventmanager.entity.Event;
import sk.kasv.robert.eventmanager.entity.User;
import sk.kasv.robert.eventmanager.entity.Category;
import sk.kasv.robert.eventmanager.entity.Location;
import sk.kasv.robert.eventmanager.repository.EventRepository;
import sk.kasv.robert.eventmanager.repository.UserRepository;
import sk.kasv.robert.eventmanager.repository.CategoryRepository;
import sk.kasv.robert.eventmanager.repository.LocationRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;

    public EventController(EventRepository eventRepository,
                           UserRepository userRepository,
                           CategoryRepository categoryRepository,
                           LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.locationRepository = locationRepository;
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/simple")
    @Operation(
            summary = "Create a new event",
            description = "Creates an event using a simple JSON object.",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\n  \"eventName\": \"Spring Concert\",\n  \"description\": \"A delightful evening of music\",\n  \"startTime\": \"2025-06-20T19:00:00\",\n  \"endTime\": \"2025-06-20T21:00:00\",\n  \"organizerId\": 1,\n  \"categoryId\": 1,\n  \"locationId\": 1\n}")
                    )
            )
    )
    public ResponseEntity<Event> createEventSimple(@RequestBody Map<String, Object> payload) {
        try {
            String eventName = (String) payload.get("eventName");
            String description = (String) payload.get("description");
            LocalDateTime startTime = LocalDateTime.parse((String) payload.get("startTime"));
            LocalDateTime endTime = LocalDateTime.parse((String) payload.get("endTime"));
            Long organizerId = Long.valueOf(String.valueOf(payload.get("organizerId")));
            Long categoryId = Long.valueOf(String.valueOf(payload.get("categoryId")));
            Long locationId = Long.valueOf(String.valueOf(payload.get("locationId")));

            Optional<User> organizerOpt = userRepository.findById(organizerId);
            Optional<Category> categoryOpt = categoryRepository.findById(categoryId);
            Optional<Location> locationOpt = locationRepository.findById(locationId);

            if (organizerOpt.isEmpty() || categoryOpt.isEmpty() || locationOpt.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            Event event = new Event();
            event.setEventName(eventName);
            event.setDescription(description);
            event.setStartTime(startTime);
            event.setEndTime(endTime);
            event.setOrganizer(organizerOpt.get());
            event.setCategory(categoryOpt.get());
            event.setLocation(locationOpt.get());

            return ResponseEntity.ok(eventRepository.save(event));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update event",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\n  \"eventName\": \"Spring Concert - Updated\",\n  \"description\": \"An updated description\",\n  \"startTime\": \"2025-06-20T19:00:00\",\n  \"endTime\": \"2025-06-20T21:30:00\"\n}")
                    )
            )
    )
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        try {
            Event event = optionalEvent.get();
            event.setEventName((String) payload.get("eventName"));
            event.setDescription((String) payload.get("description"));
            event.setStartTime(LocalDateTime.parse((String) payload.get("startTime")));
            event.setEndTime(LocalDateTime.parse((String) payload.get("endTime")));
            return ResponseEntity.ok(eventRepository.save(event));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
