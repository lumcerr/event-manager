package sk.kasv.robert.hibernate.Entity;
import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name = "course")
public class Course {
    List<Course> list;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor_Detail getInstructor_Id() {
        return instructor_Detail;
    }

    public void setInstructor_Id(Instructor_Detail instructor_Detail) {
        this.instructor_Detail = instructor_Detail;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_id")
    private Instructor_Detail instructor_Detail;

    public Course() {
    }

    public Course(String title) {
        this.title = title;
    }
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "Instructor_Id")
    private Instructor instructor;
}