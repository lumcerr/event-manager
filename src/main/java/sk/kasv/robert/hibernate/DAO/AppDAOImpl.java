package sk.kasv.robert.hibernate.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
//import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sk.kasv.robert.hibernate.entities.*;
import org.hibernate.Session;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {

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
    public void save(Review review) {
        entityManager.persist(review);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
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

    @Override
    public boolean deleteById(int id) {
        if (entityManager.find(Instructor.class, id) != null) {
            TypedQuery<Instructor> theQuery = entityManager.createQuery("DELETE FROM Instructor WHERE id =:theData", Instructor.class);
            theQuery.setParameter("theData", id);
            return true;
        }
        return false;
    }

    @Override
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void remove(Course course) {
        Course managedCourse = entityManager.find(Course.class, course.getId());
        if (managedCourse != null) {
            entityManager.remove(managedCourse);
        }
    }

    @Override
    @Transactional
    public void saveInstructorWithCourses(Instructor instructor, List<Course> courses) {
        instructor.setCourses(courses);
        entityManager.persist(instructor);
    }

    @Override
    public List<Review> getAllReviewsByCourseId(int courseId) {
        TypedQuery<Review> theQuery = entityManager.createQuery("FROM Review WHERE course_id =:theData", Review.class);
        theQuery.setParameter("theData", courseId);
        return theQuery.getResultList();
    }

    @Override
    public Course findByTitle(String title) {
        if (entityManager.createQuery("FROM Course WHERE title LIKE :title", Course.class)
                .setParameter("title", title)
                .getResultList().isEmpty()) {
            System.out.println("Course with title " + title + " does not exist.");
            return null;
        }
        return entityManager.createQuery("FROM Course WHERE title LIKE :title", Course.class)
                .setParameter("title", title)
                .getSingleResult();
    }

    @Override
    public Course findCourseById(int id) {
        TypedQuery<Course> theQuery = entityManager.createQuery("FROM Course WHERE id =:theData", Course.class);
        theQuery.setParameter("theData", id);
        return theQuery.getSingleResult();
    }

    @Override
    public Course findCourseByName(String name) {
        TypedQuery<Course> theQuery = entityManager.createQuery("FROM Course WHERE title=:theData", Course.class);
        theQuery.setParameter("theData", name);
        return theQuery.getSingleResult();
    }

    @Override
    public List<Course> getAllCourses() {
        TypedQuery<Course> theQuery = entityManager.createQuery("FROM Course", Course.class);
        return theQuery.getResultList();
    }


}
