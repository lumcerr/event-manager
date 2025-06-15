package sk.kasv.robert.eventmanager.repository;

import sk.kasv.robert.eventmanager.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    Optional<Participant> findByEmail(String email);
    List<Participant> findByFullNameContainingIgnoreCase(String name);
    boolean existsByEmail(String email);
}
