package sk.kasv.robert.hibernate.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class Review {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "comment")
    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private int course_id;

    private  Review(){}
    public Review(int id, String comment, int course_id){
        this.id=id;
        this.comment=comment;
        this.course_id=course_id;
        
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", course_id=" + course_id +
                '}';
    }


    public void add(Review review) {
    }
}
