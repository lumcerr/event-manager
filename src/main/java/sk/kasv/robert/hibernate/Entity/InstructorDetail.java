package sk.kasv.robert.hibernate.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class InstructorDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
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



    public InstructorDetail(){}
    public InstructorDetail(String youtube,String hobbies){
        this.youtube=youtube;
        this.hobbies=hobbies;
    }
}
