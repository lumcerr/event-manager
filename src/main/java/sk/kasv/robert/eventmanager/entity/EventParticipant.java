package sk.kasv.robert.eventmanager.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class EventParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    private LocalDateTime registeredAt;

    // Default constructor
    public EventParticipant() {}

    public EventParticipant(Event event, Participant participant, LocalDateTime registeredAt) {
        this.event = event;
        this.participant = participant;
        this.registeredAt = registeredAt;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }


    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }
}
