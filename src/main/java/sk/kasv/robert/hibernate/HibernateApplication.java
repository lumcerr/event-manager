package sk.kasv.robert.hibernate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sk.kasv.robert.hibernate.DAO.AppDAO;
import sk.kasv.robert.hibernate.DAO.StudentDAO;
import sk.kasv.robert.hibernate.Entity.Course;
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
		/*	System.out.println("Student DAO starting");
			System.out.println("---------");
			createStudent(studentDAO);
			readStudent(studentDAO);
			System.out.println("---------");
			queryForStudents(studentDAO);
			System.out.println("---------");
			queryForStudentsByLastName(studentDAO);
			System.out.println("---------");
			updateStudent(studentDAO);
			System.out.println("---------");
			queryForStudents(studentDAO);*/
		};
	}

	@Bean
	public CommandLineRunner commandLineRunner2(AppDAO appDAO) {
		return runner -> {
			System.out.println("Instructor DAO starting");
			System.out.println("---------");
			createInstructor(appDAO);
			queryForInstructor(appDAO);
			System.out.println("-----Find by ID----");
			findById(appDAO);
			System.out.println("-----Find by email----");
			queryForInstructorByEmail(appDAO);
			System.out.println("---------");
			createInstructorWithCourses(appDAO);
			System.out.println("---------");
		};
	}

	private void queryForInstructor(AppDAO appDAO) {
		int id = 5;
		System.out.println("Query for Instructor");
		Instructor instructor = appDAO.findById(id);
		System.out.println(instructor);
		System.out.println(instructor.getInstructorDetail());
	}

	private void findById(AppDAO appDAO) {
		int theId = 1;
		Instructor myInstructor = appDAO.findById(theId);
		System.out.println("Found the instructor: " + myInstructor);
	}

	private void queryForInstructorByEmail(AppDAO appDAO) {
		List<Instructor> instructors = appDAO.findByEmail("%caj%");

		for (Instructor instructor : instructors) {
			System.out.println(instructor.toString());
			System.out.println(instructor.getInstructorDetail().toString());
		}
	}

	private void deleteInstructorById(AppDAO appDAO) {
		int theId = 7;
		System.out.println((appDAO.deleteById(theId) ? "Instructor deleted successfully" : "Couldn't find instructor"));
	}

	private void createInstructor(AppDAO appDAO) {
		Instructor tempInstructor = new Instructor("Pavol", "cajka", "cajkaP@gmail.com");
		InstructorDetail tempInstructorDetail = new InstructorDetail("cajkasta","basketball");
		tempInstructor.setInstructorDetail(tempInstructorDetail);
		appDAO.save(tempInstructor);
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor tempInstructor = new Instructor("Miro", "diro", "valekm@seznam.cz");
		InstructorDetail detail = new InstructorDetail("http://youtobe/java", "reading books");
		tempInstructor.setInstructorDetail(detail);

		Course course1 = new Course("UX Design");
		Course course2 = new Course("Managment");
		tempInstructor.add(course1);
		tempInstructor.add(course2);
		System.out.println();
		appDAO.save(tempInstructor);
	}

	private void updateInstructorsCourse(AppDAO appDAO) {
		Instructor tempInstructor = appDAO.findById(1);
		List<Course> courses = tempInstructor.getCourses();
		courses.add(new Course("Pc games"));
		appDAO.saveInstructorWithCourses(tempInstructor, courses);
		System.out.println("updated courses of Instructor with id 1");
	}

	private void findCourseByTitle(AppDAO appDao) {
		String title = "Testing";
		Course course = appDao.findByTitle(title);
		System.out.println("Course with title " + title + " is: " + course);
	}

	private void createStudent(StudentDAO studentDAO) {
		System.out.println("Creating a new student object...");
		Student tempStudent = new Student("Orol", "Novak", "novakm@gmail.com");
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}

	private void readStudent(StudentDAO studentDAO) {
		int theId=2;
		Student myStudent = studentDAO.findById(theId);
		System.out.println("Found the student: " + myStudent);
	}

	private void queryForStudents(StudentDAO studentDAO) {
		List<Student> theStudents = studentDAO.findAll();
		for (Student student : theStudents) {
			System.out.println(student.toString());
		}
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		List<Student> theStudents = studentDAO.findByLastName("%ovak");
		for (Student student : theStudents) {
			System.out.println(student.toString());
		}
	}

	private void updateStudent(StudentDAO studentDAO) {
		Student student = studentDAO.findById(2);
		student.setLastName("Zvolenský");
		studentDAO.update(student);
	}


}

