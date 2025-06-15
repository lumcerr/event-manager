package sk.kasv.robert.eventmanager.controller;

import sk.kasv.robert.eventmanager.entity.EventParticipant;
import sk.kasv.robert.eventmanager.service.EventParticipantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/eventParticipants")
public class EventParticipantController {

    private final EventParticipantService eventParticipantService;

    // Constructor injection for EventParticipantService dependency
    public EventParticipantController(EventParticipantService eventParticipantService) {
        this.eventParticipantService = eventParticipantService;
    }

    // POST /api/eventParticipants - Register a participant to an event
    @PostMapping
    public ResponseEntity<EventParticipant> registerParticipant(@RequestBody EventParticipant eventParticipant) {
        EventParticipant registered = eventParticipantService.registerParticipant(eventParticipant);
        return new ResponseEntity<>(registered, HttpStatus.CREATED);
    }

    // GET /api/eventParticipants/event?eventId=xxx - Get all registrations for a given event
    @GetMapping("/event")
    public ResponseEntity<List<EventParticipant>> getRegistrationsByEventId(@RequestParam Long eventId) {
        List<EventParticipant> registrations = eventParticipantService.getRegistrationsByEventId(eventId);
        return ResponseEntity.ok(registrations);
    }

    // GET /api/eventParticipants/participant?participantId=xxx - Get all registrations for a given participant
    @GetMapping("/participant")
    public ResponseEntity<List<EventParticipant>> getRegistrationsByParticipantId(@RequestParam Long participantId) {
        List<EventParticipant> registrations = eventParticipantService.getRegistrationsByParticipantId(participantId);
        return ResponseEntity.ok(registrations);
    }

    // GET /api/eventParticipants/lookup?eventId=xxx&participantId=yyy - Check registration by event and participant
    @GetMapping("/lookup")
    public ResponseEntity<EventParticipant> getRegistrationByEventAndParticipant(@RequestParam Long eventId,
                                                                                 @RequestParam Long participantId) {
        Optional<EventParticipant> registration = eventParticipantService.getRegistrationByEventIdAndParticipantId(eventId, participantId);
        return registration.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/eventParticipants/count?eventId=xxx - Count registrations for a specific event
    @GetMapping("/count")
    public ResponseEntity<Long> countRegistrationsForEvent(@RequestParam Long eventId) {
        long count = eventParticipantService.countRegistrationsForEvent(eventId);
        return ResponseEntity.ok(count);
    }

    // DELETE /api/eventParticipants/{id} - Remove a specific registration
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeRegistration(@PathVariable Long id) {
        eventParticipantService.removeRegistration(id);
        return ResponseEntity.noContent().build();
    }
}
