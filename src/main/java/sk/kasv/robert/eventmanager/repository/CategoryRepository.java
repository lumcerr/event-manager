package sk.kasv.robert.eventmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.kasv.robert.eventmanager.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
