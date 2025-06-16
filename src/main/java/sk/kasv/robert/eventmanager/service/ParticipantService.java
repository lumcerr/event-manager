package sk.kasv.robert.eventmanager.service;

import sk.kasv.robert.eventmanager.entity.Participant;
import sk.kasv.robert.eventmanager.repository.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public Participant createParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    public Optional<Participant> getParticipantById(Long id) {
        return participantRepository.findById(id);
    }

    public Optional<Participant> getParticipantByEmail(String email) {
        return participantRepository.findByEmail(email);
    }

    public List<Participant> searchParticipantsByName(String name) {
        return participantRepository.findByFullNameContainingIgnoreCase(name);
    }

    public boolean participantExistsByEmail(String email) {
        return participantRepository.existsByEmail(email);
    }

    public Optional<Participant> updateParticipant(Long id, Participant updatedParticipant) {
        return participantRepository.findById(id).map(participant -> {
            participant.setFullName(updatedParticipant.getFullName());
            participant.setEmail(updatedParticipant.getEmail());
            return participantRepository.save(participant);
        });
    }

    public void deleteParticipant(Long id) {
        participantRepository.deleteById(id);
    }
}
