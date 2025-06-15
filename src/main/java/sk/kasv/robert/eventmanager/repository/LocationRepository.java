package sk.kasv.robert.eventmanager.repository;

import sk.kasv.robert.eventmanager.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByCityIgnoreCase(String city);
    List<Location> findByAddressContainingIgnoreCase(String address);
}
