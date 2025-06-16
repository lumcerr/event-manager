package sk.kasv.robert.eventmanager.entity;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;

    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL)
    private List<EventParticipant> eventParticipants;

    public Participant() {}

    // Parameterized constructor
    public Participant(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<EventParticipant> getEventParticipants() {
        return eventParticipants;
    }

    public void setEventParticipants(List<EventParticipant> eventParticipants) {
        this.eventParticipants = eventParticipants;
    }
}
