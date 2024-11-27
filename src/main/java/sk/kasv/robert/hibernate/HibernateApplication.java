package sk.kasv.robert.hibernate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sk.kasv.robert.hibernate.DAO.AppDAO;
import sk.kasv.robert.hibernate.DAO.StudentDAO;
import sk.kasv.robert.hibernate.Entity.Instructor;
import sk.kasv.robert.hibernate.Entity.InstructorDetail;
import sk.kasv.robert.hibernate.Entity.Student;
import java.util.List;
@SpringBootApplication
public class HibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			System.out.println("Student DAO App starting");
			//createStudent(studentDAO);
			//readStudent(studentDAO);
			//updateStudent(studentDAO);
			//readStudent(studentDAO);
		};
	}
	@Bean
	public CommandLineRunner commandLineRunner2(AppDAO appDAO) {
		return runner -> {
			System.out.println(" App DAO starting");
			queryForInstructor(appDAO);
		};
	}

	private void queryForInstructor (AppDAO appDAO){
		int theId=1;
		System.out.println("Querry for instructor");
		Instructor instructor = appDAO.instructorFindByID(theId);
		System.out.println(instructor);
		System.out.println(instructor.getInstructorDetail());
	}

	private static void createInstructor(AppDAO appDAO){
		Instructor tempInstructor = new Instructor("Jozef","Mak","jozef.mak@gmail.com");
		InstructorDetail detail = new InstructorDetail("Database","Reading");
	}


	private void createStudent(StudentDAO studentDAO) {
		System.out.println("Creating a new student object...");
		Student tempStudent = new Student("Tomas", "Novak", "novakt@gmail.com");
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}

	private void readStudent(StudentDAO studentDAO) {
		int theId=2;
		Student myStudent = studentDAO.findById(theId);
		System.out.println("Found the student: " + myStudent);
	}

	private void queryForStudents(StudentDAO studentDAO){
		System.out.println("All students:");
		List<Student> theStudents = studentDAO.findAll();
		for(Student student:theStudents)
			System.out.println(student);
		System.out.println("------------------------");
	}


	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		String lastName = "Connor";
		System.out.println("Student with lastname: "+lastName);
		List<Student> theStudents = studentDAO.findByLastName(lastName);
		for (Student student : theStudents) {
			System.out.println(student);
		}
	}

	private void updateStudent(StudentDAO studentDAO) {
		Student theStudent = studentDAO.findById(2);
		theStudent.setFirstName("Despacito");
		theStudent.setLastName("Lover");
		theStudent.setEmail("lover.d@gmail.com");
		studentDAO.update(theStudent);
		System.out.println("Student successfully updated: "+theStudent);
	}

}

