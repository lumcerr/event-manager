package sk.kasv.robert.hibernate.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sk.kasv.robert.hibernate.entities.Course;
import sk.kasv.robert.hibernate.entities.Student;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE lastName LIKE :theData", Student.class);
        theQuery.setParameter("theData", lastName);

        return theQuery.getResultList();
    }

    @Transactional
    @Override
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Transactional
    @Override
    public void delete(int id) {
        Student tempStudent = findById(id);
        if (tempStudent != null) {
            for (Course course : tempStudent.getCourses()) {
                course.remove(tempStudent);
            }

            entityManager.remove(tempStudent);
        }
        else {
            System.out.println("Student with id " + id + " not found.");
        }

    }

    @Override
    @Transactional
    public void merge(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void remove(Student student) {
        Student managedStudent = entityManager.find(Student.class, student.getId());
        if (managedStudent != null) {
            entityManager.remove(managedStudent);
        }
    }

    @Transactional
    @Override
    public void removeStudentFromCourse(int studentId, int courseId) {
        Query query = entityManager.createNativeQuery(
                "DELETE FROM course_student WHERE course_id = :courseId AND student_id = :studentId");
        query.setParameter("courseId", courseId);
        query.setParameter("studentId", studentId);

        query.executeUpdate();
    }
}
