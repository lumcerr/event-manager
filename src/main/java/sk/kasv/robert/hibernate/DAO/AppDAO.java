package sk.kasv.robert.hibernate.DAO;

import sk.kasv.robert.hibernate.entities.Course;
import sk.kasv.robert.hibernate.entities.Instructor;
import sk.kasv.robert.hibernate.entities.InstructorDetail;
import sk.kasv.robert.hibernate.entities.Review;

import java.util.List;

public interface AppDAO {
    public void save(Instructor instructor);

    public void save(Review review);
    public void save(Course course);
    public void update(Course course);

    public Instructor findById(int id);

    public Instructor findByLastName(String name);

    public List<Instructor> findByEmail(String mail);

    public boolean deleteById(int id);

    Course findByTitle(String title);

    public void update(Instructor instructor);

    public void saveInstructorWithCourses(Instructor instructor, List<Course> courses);

    List<Review> getAllReviewsByCourseId(int courseId);

    Course findCourseById(int id);
    Course findCourseByName(String name);
    List<Course> getAllCourses();
    public void remove(Course course);
}
