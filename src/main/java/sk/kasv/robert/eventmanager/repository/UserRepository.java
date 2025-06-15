package sk.kasv.robert.eventmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.kasv.robert.eventmanager.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // Custom queries if needed
}
