package sk.kasv.robert.eventmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.kasv.robert.eventmanager.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
