package sk.kasv.robert.eventmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.kasv.robert.eventmanager.entity.Participant;
import sk.kasv.robert.eventmanager.repository.ParticipantRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    private final ParticipantRepository participantRepository;

    public ParticipantController(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @GetMapping
    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participant> getParticipantById(@PathVariable Long id) {
        Optional<Participant> participant = participantRepository.findById(id);
        return participant.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
            summary = "Create a participant",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\n  \"fullName\": \"Alice Smith\",\n  \"email\": \"alice.smith@example.com\"\n}")
                    )
            )
    )
    public ResponseEntity<Participant> createParticipant(@RequestBody Map<String, String> payload) {
        String fullName = payload.get("fullName");
        String email = payload.get("email");
        Participant participant = new Participant();
        participant.setFullName(fullName);
        participant.setEmail(email);
        return ResponseEntity.ok(participantRepository.save(participant));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a participant",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\n  \"fullName\": \"Alice S. Updated\",\n  \"email\": \"alice.updated@example.com\"\n}")
                    )
            )
    )
    public ResponseEntity<Participant> updateParticipant(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        Optional<Participant> optionalParticipant = participantRepository.findById(id);
        if (optionalParticipant.isPresent()) {
            Participant participant = optionalParticipant.get();
            participant.setFullName(payload.get("fullName"));
            participant.setEmail(payload.get("email"));
            return ResponseEntity.ok(participantRepository.save(participant));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Long id) {
        if (participantRepository.existsById(id)) {
            participantRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
