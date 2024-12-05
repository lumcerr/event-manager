package sk.kasv.robert.hibernate.DAO;

import sk.kasv.robert.hibernate.Entity.Course;
import sk.kasv.robert.hibernate.Entity.Instructor;

import java.util.List;

public interface AppDAO {
    public void save(Instructor instructor);

    public Instructor findById(int id);

    public Instructor findByLastName(String name);

    public List<Instructor> findByEmail(String mail);

    public boolean deleteById(int id);

    Course findByTitle(String title);

    public void update(Instructor instructor);

    public void saveInstructorWithCourses(Instructor instructor, List<Course> courses);
}
