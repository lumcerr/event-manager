package sk.kasv.robert.hibernate.DAO;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sk.kasv.robert.hibernate.Entity.Student;

import javax.swing.text.html.parser.Entity;

@Repository
public class StudentDAOImpl implements StudentDAO{
    private EntityManager entityManager;
    @Autowired
    public StudentDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    @Transactional
    public void save(Student theStudent) {entityManager.persist(theStudent);

    }
}
