package sk.kasv.robert.eventmanager.repository;

import sk.kasv.robert.eventmanager.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByCategoryName(String categoryName);
    List<Event> findByOrganizerId(Long organizerId);
    List<Event> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Event> findByLocationCityIgnoreCase(String city);
}
