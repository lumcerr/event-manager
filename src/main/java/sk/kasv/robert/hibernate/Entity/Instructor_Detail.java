package sk.kasv.robert.hibernate.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "instructor_detail")
public class Instructor_Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    @Column(name = "youtube_channel")
    private String youtube;
    @Column(name = "hobby")
    private String hobbies;

    public int getId() {
        return id;
    }

    public String getYoutube() {
        return youtube;
    }

    public String getHobbies() {
        return hobbies;
    }



    public Instructor_Detail(){}
    public Instructor_Detail(String youtube, String hobbies){
        this.youtube=youtube;
        this.hobbies=hobbies;
    }

    @Override
    public String toString() {
        return "InstructorDetail{" +
                "id=" + id +
                ", youtube='" + youtube + '\'' +
                ", hobbies='" + hobbies + '\'' +
                '}';
    }
}
