package sk.kasv.robert.eventmanager.service;

import sk.kasv.robert.eventmanager.entity.EventParticipant;
import sk.kasv.robert.eventmanager.repository.EventParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventParticipantService {

    private final EventParticipantRepository eventParticipantRepository;

    public EventParticipantService(EventParticipantRepository eventParticipantRepository) {
        this.eventParticipantRepository = eventParticipantRepository;
    }

    public EventParticipant registerParticipant(EventParticipant eventParticipant) {
        // Optionally: Check for duplicate registration or validate business rules here.
        return eventParticipantRepository.save(eventParticipant);
    }

    public List<EventParticipant> getRegistrationsByEventId(Long eventId) {
        return eventParticipantRepository.findByEventId(eventId);
    }

    public List<EventParticipant> getRegistrationsByParticipantId(Long participantId) {
        return eventParticipantRepository.findByParticipantId(participantId);
    }

    public Optional<EventParticipant> getRegistrationByEventIdAndParticipantId(Long eventId, Long participantId) {
        return eventParticipantRepository.findByEventIdAndParticipantId(eventId, participantId);
    }

    public long countRegistrationsForEvent(Long eventId) {
        return eventParticipantRepository.countByEventId(eventId);
    }

    public void removeRegistration(Long registrationId) {
        eventParticipantRepository.deleteById(registrationId);
    }
}
