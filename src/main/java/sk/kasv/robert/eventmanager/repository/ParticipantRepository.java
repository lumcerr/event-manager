package sk.kasv.robert.eventmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.kasv.robert.eventmanager.entity.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
