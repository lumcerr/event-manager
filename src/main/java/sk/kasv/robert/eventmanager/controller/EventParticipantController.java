package sk.kasv.robert.eventmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.kasv.robert.eventmanager.entity.EventParticipant;
import sk.kasv.robert.eventmanager.entity.Event;
import sk.kasv.robert.eventmanager.entity.Participant;
import sk.kasv.robert.eventmanager.repository.EventParticipantRepository;
import sk.kasv.robert.eventmanager.repository.EventRepository;
import sk.kasv.robert.eventmanager.repository.ParticipantRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/eventparticipants")
public class EventParticipantController {

    private final EventParticipantRepository eventParticipantRepository;
    private final EventRepository eventRepository;
    private final ParticipantRepository participantRepository;

    public EventParticipantController(EventParticipantRepository eventParticipantRepository,
                                      EventRepository eventRepository,
                                      ParticipantRepository participantRepository) {
        this.eventParticipantRepository = eventParticipantRepository;
        this.eventRepository = eventRepository;
        this.participantRepository = participantRepository;
    }

    @GetMapping
    public List<EventParticipant> getAllEventParticipants() {
        return eventParticipantRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventParticipant> getEventParticipantById(@PathVariable Long id) {
        Optional<EventParticipant> ep = eventParticipantRepository.findById(id);
        return ep.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
            summary = "Register participant for an event",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\n  \"eventId\": 1,\n  \"participantId\": 2\n}")
                    )
            )
    )
    public ResponseEntity<EventParticipant> createEventParticipant(@RequestBody Map<String, Object> payload) {
        try {
            Long eventId = Long.valueOf(String.valueOf(payload.get("eventId")));
            Long participantId = Long.valueOf(String.valueOf(payload.get("participantId")));

            Optional<Event> eventOpt = eventRepository.findById(eventId);
            Optional<Participant> participantOpt = participantRepository.findById(participantId);
            if (eventOpt.isEmpty() || participantOpt.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            EventParticipant eventParticipant = new EventParticipant();
            eventParticipant.setEvent(eventOpt.get());
            eventParticipant.setParticipant(participantOpt.get());
            eventParticipant.setRegisteredAt(LocalDateTime.now());

            return ResponseEntity.ok(eventParticipantRepository.save(eventParticipant));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update event participant",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\n  \"registeredAt\": \"2025-06-20T18:45:00\"\n}")
                    )
            )
    )
    public ResponseEntity<EventParticipant> updateEventParticipant(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        Optional<EventParticipant> optionalEP = eventParticipantRepository.findById(id);
        if (optionalEP.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        try {
            EventParticipant ep = optionalEP.get();
            if(payload.containsKey("registeredAt")) {
                String regTimeStr = (String) payload.get("registeredAt");
                ep.setRegisteredAt(LocalDateTime.parse(regTimeStr));
            }
            return ResponseEntity.ok(eventParticipantRepository.save(ep));
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventParticipant(@PathVariable Long id) {
        if(eventParticipantRepository.existsById(id)) {
            eventParticipantRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
