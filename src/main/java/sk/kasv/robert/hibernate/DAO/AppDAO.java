package sk.kasv.robert.hibernate.DAO;

import sk.kasv.robert.hibernate.Entity.Instructor;

import java.util.List;

public interface AppDAO {
    public void save (Instructor theInstructor);

    Instructor findById(int theId);

   public Instructor findByLastName(String name);

    public List<Instructor> findByEmail(String mail);
}
