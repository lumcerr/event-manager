package sk.kasv.robert.eventmanager.service;

import sk.kasv.robert.eventmanager.entity.Participant;
import sk.kasv.robert.eventmanager.repository.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    // Constructor injection
    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    // Create a new participant
    public Participant createParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    // Retrieve all participants
    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    // Retrieve a participant by ID
    public Optional<Participant> getParticipantById(Long id) {
        return participantRepository.findById(id);
    }

    // Retrieve a participant by email
    public Optional<Participant> getParticipantByEmail(String email) {
        return participantRepository.findByEmail(email);
    }

    // Search participants by a portion of their full name (case-insensitive)
    public List<Participant> searchParticipantsByName(String name) {
        return participantRepository.findByFullNameContainingIgnoreCase(name);
    }

    // Check if a participant exists by email
    public boolean participantExistsByEmail(String email) {
        return participantRepository.existsByEmail(email);
    }

    // Update an existing participant
    public Optional<Participant> updateParticipant(Long id, Participant updatedParticipant) {
        return participantRepository.findById(id).map(participant -> {
            participant.setFullName(updatedParticipant.getFullName());
            participant.setEmail(updatedParticipant.getEmail());
            return participantRepository.save(participant);
        });
    }

    // Delete a participant by ID
    public void deleteParticipant(Long id) {
        participantRepository.deleteById(id);
    }
}
