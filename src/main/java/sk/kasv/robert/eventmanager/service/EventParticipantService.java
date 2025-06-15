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

    // Register a participant to an event
    public EventParticipant registerParticipant(EventParticipant eventParticipant) {
        // Optionally: Check for duplicate registration or validate business rules here.
        return eventParticipantRepository.save(eventParticipant);
    }

    // Retrieve all registrations for a given event by event ID
    public List<EventParticipant> getRegistrationsByEventId(Long eventId) {
        return eventParticipantRepository.findByEventId(eventId);
    }

    // Retrieve all registrations for a given participant by participant ID
    public List<EventParticipant> getRegistrationsByParticipantId(Long participantId) {
        return eventParticipantRepository.findByParticipantId(participantId);
    }

    // Check if a participant is already registered for a specific event
    public Optional<EventParticipant> getRegistrationByEventIdAndParticipantId(Long eventId, Long participantId) {
        return eventParticipantRepository.findByEventIdAndParticipantId(eventId, participantId);
    }

    // Count the number of registrations for a specific event (useful for enforcing capacity limits)
    public long countRegistrationsForEvent(Long eventId) {
        return eventParticipantRepository.countByEventId(eventId);
    }

    // Remove a registration by its ID
    public void removeRegistration(Long registrationId) {
        eventParticipantRepository.deleteById(registrationId);
    }
}
