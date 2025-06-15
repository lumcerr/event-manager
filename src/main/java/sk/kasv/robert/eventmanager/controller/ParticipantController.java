package sk.kasv.robert.eventmanager.controller;

import sk.kasv.robert.eventmanager.entity.Participant;
import sk.kasv.robert.eventmanager.service.ParticipantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    private final ParticipantService participantService;

    // Constructor injection of ParticipantService
    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    // GET /api/participants - Retrieve all participants
    @GetMapping
    public ResponseEntity<List<Participant>> getAllParticipants() {
        List<Participant> participants = participantService.getAllParticipants();
        return ResponseEntity.ok(participants);
    }

    // GET /api/participants/{id} - Retrieve a participant by ID
    @GetMapping("/{id}")
    public ResponseEntity<Participant> getParticipantById(@PathVariable Long id) {
        Optional<Participant> participantOptional = participantService.getParticipantById(id);
        return participantOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/participants/search?name=xyz - Search participants by full name (partial, case-insensitive)
    @GetMapping("/search")
    public ResponseEntity<List<Participant>> searchParticipantsByName(@RequestParam String name) {
        List<Participant> participants = participantService.searchParticipantsByName(name);
        return ResponseEntity.ok(participants);
    }

    // GET /api/participants/email?email=xyz - Retrieve a participant by email
    @GetMapping("/email")
    public ResponseEntity<Participant> getParticipantByEmail(@RequestParam String email) {
        Optional<Participant> participantOptional = participantService.getParticipantByEmail(email);
        return participantOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/participants - Create a new participant
    @PostMapping
    public ResponseEntity<Participant> createParticipant(@RequestBody Participant participant) {
        Participant createdParticipant = participantService.createParticipant(participant);
        return new ResponseEntity<>(createdParticipant, HttpStatus.CREATED);
    }

    // PUT /api/participants/{id} - Update an existing participant
    @PutMapping("/{id}")
    public ResponseEntity<Participant> updateParticipant(@PathVariable Long id, @RequestBody Participant participant) {
        Optional<Participant> updatedParticipant = participantService.updateParticipant(id, participant);
        return updatedParticipant.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/participants/{id} - Delete a participant by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Long id) {
        participantService.deleteParticipant(id);
        return ResponseEntity.noContent().build();
    }
}
