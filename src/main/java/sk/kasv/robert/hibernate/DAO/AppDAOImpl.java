package sk.kasv.robert.hibernate.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sk.kasv.robert.hibernate.Entity.Instructor;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{
    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    @Transactional
    public Instructor findById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public Instructor findByLastName(String name) {
        return entityManager.find(Instructor.class, name);
    }

    @Override
    @Transactional
    public List<Instructor> findByEmail(String mail) {
        TypedQuery<Instructor> theQuery = entityManager.createQuery("FROM Instructor WHERE email LIKE :theData", Instructor.class);
        theQuery.setParameter("theData", mail);

        return theQuery.getResultList();
    }

}
