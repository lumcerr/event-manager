package sk.kasv.robert.hibernate.DAO;

import org.springframework.stereotype.Repository;
import sk.kasv.robert.hibernate.entities.Student;
import java.util.List;

@Repository
public interface StudentDAO {
    void save(Student theStudent);
    Student findById(Integer id);

    List<Student> findAll();
    List<Student> findByLastName(String lastName);

    void update(Student theStudent);
    void delete(int id);
    void merge(Student student);

    void remove(Student student);

    void removeStudentFromCourse(int studentId, int courseId);
}
