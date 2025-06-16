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

    public EventParticipantController(EventParticipantService eventParticipantService) {
        this.eventParticipantService = eventParticipantService;
    }

    @PostMapping
    public ResponseEntity<EventParticipant> registerParticipant(@RequestBody EventParticipant eventParticipant) {
        EventParticipant registered = eventParticipantService.registerParticipant(eventParticipant);
        return new ResponseEntity<>(registered, HttpStatus.CREATED);
    }

    @GetMapping("/event")
    public ResponseEntity<List<EventParticipant>> getRegistrationsByEventId(@RequestParam Long eventId) {
        List<EventParticipant> registrations = eventParticipantService.getRegistrationsByEventId(eventId);
        return ResponseEntity.ok(registrations);
    }

    @GetMapping("/participant")
    public ResponseEntity<List<EventParticipant>> getRegistrationsByParticipantId(@RequestParam Long participantId) {
        List<EventParticipant> registrations = eventParticipantService.getRegistrationsByParticipantId(participantId);
        return ResponseEntity.ok(registrations);
    }

    @GetMapping("/lookup")
    public ResponseEntity<EventParticipant> getRegistrationByEventAndParticipant(@RequestParam Long eventId,
                                                                                 @RequestParam Long participantId) {
        Optional<EventParticipant> registration = eventParticipantService.getRegistrationByEventIdAndParticipantId(eventId, participantId);
        return registration.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countRegistrationsForEvent(@RequestParam Long eventId) {
        long count = eventParticipantService.countRegistrationsForEvent(eventId);
        return ResponseEntity.ok(count);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeRegistration(@PathVariable Long id) {
        eventParticipantService.removeRegistration(id);
        return ResponseEntity.noContent().build();
    }
}
