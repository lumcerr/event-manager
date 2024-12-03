package sk.kasv.robert.hibernate.DAO;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sk.kasv.robert.hibernate.Entity.Course;
import sk.kasv.robert.hibernate.Entity.Instructor;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{
    private final EntityManager entityManager;
    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findById(int theId) {
        return null;
    }

    @Override
    public Instructor findByLastName(String name) {
        return null;
    }

    @Override
    public List<Instructor> findByEmail(String mail) {
        return null;
    }

    @Override
    public Instructor instructorFindByID(int theId) {
        return entityManager.find(Instructor.class,theId);
    }

    @Override
    public Course findCourseByTitle(String title) {
        return entityManager.find(Course.class,title);
    }


}
