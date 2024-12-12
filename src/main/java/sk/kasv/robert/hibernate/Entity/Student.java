package sk.kasv.robert.hibernate.Entity;

import jakarta.persistence.*;
import sk.kasv.robert.hibernate.DAO.StudentDAO;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    private String firstName;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

   public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;

    public Student() {

    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    private void queryForStudentsByLastName(StudentDAO studentDAO){
        List<Student> theStudents= studentDAO.findByLastName("novak");
        for(Student student:theStudents)
         System.out.println(student);
    }
    private void queryForStudents(StudentDAO studentDAO){
        List<Student> theStudents = studentDAO.findAll();
        for (Student student:theStudents)
            System.out.println(student);
    }

    private void readStudent(StudentDAO studentDAO){}
    private void createStudent(StudentDAO studentDAO){}

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    @JoinTable(name = "student_course", joinColumns = @JoinColumn(name =  "course_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Course> courses;

    public void addCourse(Course course) {
        if (course == null) {
            return;
        }
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }
}
