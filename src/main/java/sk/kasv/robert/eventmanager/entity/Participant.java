package sk.kasv.robert.eventmanager.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Participant {

    @EmbeddedId
    private ParticipantId id = new ParticipantId();

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("eventId")
    @JoinColumn(name = "event_id")
    private Event event;

    private LocalDateTime registrationDate;
    private boolean confirmed;

    public Participant() {}

    public Participant(User user, Event event, LocalDateTime registrationDate, boolean confirmed) {
        this.user = user;
        this.event = event;
        this.registrationDate = registrationDate;
        this.confirmed = confirmed;
        this.id = new ParticipantId(user.getId(), event.getId());
    }

    // Getters and Setters

    public ParticipantId getId() {
        return id;
    }

    public void setId(ParticipantId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
