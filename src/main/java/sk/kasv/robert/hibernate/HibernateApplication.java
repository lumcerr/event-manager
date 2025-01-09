package sk.kasv.robert.hibernate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sk.kasv.robert.hibernate.DAO.AppDAO;
import sk.kasv.robert.hibernate.DAO.StudentDAO;
import sk.kasv.robert.hibernate.entities.*;

import java.util.List;

@SpringBootApplication
public class HibernateApplication {

	private int id = 3;

	public static void main(String[] args) {
		SpringApplication.run(HibernateApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			/*
			System.out.println("App starting");
			System.out.println("---------");
			//createStudent(studentDAO);
			readStudent(studentDAO);
			System.out.println("---------");
			queryForStudents(studentDAO);
			System.out.println("---------");
			queryForStudentsByLastName(studentDAO);
			System.out.println("---------");
			updateStudent(studentDAO);
			System.out.println("---------");
			queryForStudents(studentDAO);
			*/
		};
	}

	@Bean
	public CommandLineRunner commandLineRunner2(AppDAO appDAO) {
		return runner -> {
			/*
			System.out.println("AppDAO starting");
			System.out.println("---------");
			createInstructor(appDAO);
			queryForInstructor(appDAO);
			System.out.println("-----Find by ID----");
			findById(appDAO);
			System.out.println("-----Find by email----");
			queryForInstructorByEmail(appDAO);
			System.out.println("_:_:_:_:_:_:_:_:_:_:_:_:_:_:_:_");
			//createInstructorWithCourses(appDAO);
			System.out.println("_:_:_:_:_:_:_:_:_:_:_:_:_:_:_:_");*/
			/*System.out.println("Command Line Runner 2");
			queryForReviewsInCourseById(appDAO);
			insertReview(appDAO);
			queryForReviewsInCourseById(appDAO);
			updateReview(appDAO);
			queryForReviewsInCourseById(appDAO);*/
		};
	}

	@Bean
	public CommandLineRunner commandLineRunner3(StudentDAO studentDAO, AppDAO appDAO) {
		return runner -> {
			System.out.println("##########\nVýpis študentov\n##########");
			queryForStudents(studentDAO);

			System.out.println("##########\nPridal sa žiak Xenia Lavrencova\n##########");
			createStudent(studentDAO);

			System.out.println("##########\nVýpis študentov\n##########");
			queryForStudents(studentDAO);

			System.out.println("##########\nVytvorí sa course 'Python pre pokročilých' a nový Instructor 'Miro Válek'\n##########");
			createInstructorWithCourses(appDAO);

			System.out.println("##########\nVypis kurzov\n##########");
			queryForCourses(appDAO);

			System.out.println("##########\nPridá Course Študentovi Xenia\n##########");
			addStudentToCourse(studentDAO, appDAO, "Python pre pokročilých", "Lavrencova");

			System.out.println("##########\nVypíše študenta Xenia aj s courses\n##########");
			queryForStudentByLastName(studentDAO);

			System.out.println("##########\nVypíše Course CSS\n##########");
			readCourseByNameWithStudents(appDAO, "CSS");

			System.out.println("##########\nPridá Xenii course z DB\n##########");
			addCourseToStudent(studentDAO, appDAO, "CSS", "Lavrencova");
			//addStudentToCourse(studentDAO, appDAO, "CSS", "Lavrencova");

			System.out.println("##########\nVypíše študenta Xenia aj s courses\n##########");
			queryForStudentByLastName(studentDAO);

			System.out.println("##########\nVypíše Course CSS + students\n##########");
			readCourseByNameWithStudents(appDAO, "CSS");

			System.out.println("##########\nVytiahne course CSS a premenuje ho na CSS3\n##########");
			renameCourse(appDAO, "CSS", "CSS3");

			System.out.println("##########\nZ Course CSS3 odoberé študenta s id 11\n##########");
			removeStudentFromCourse(appDAO, studentDAO);

			System.out.println("##########\nDo Course CSS3 pridá nového študenta\n##########");
			insertStudentIntoCourse(appDAO, studentDAO);

			System.out.println("##########\nVypíše Course CSS3 + students\n##########");
			readCourseByNameWithStudents(appDAO, "CSS3");

			System.out.println("##########\nVymaže študenta, ktorý ma nejaký kurz (Ivy King)");
			deleteStudent(studentDAO);

			System.out.println("##########\nVypis course CSS3 so Študentmi\n##########");
			readCourseByNameWithStudents(appDAO, "CSS3");
		};
	}

	private void deleteStudent(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findByLastName("King");
		//students.getFirst().getCourses().clear();
		studentDAO.delete(students.getFirst().getId());
	}

	private void readCourseByNameWithStudents(AppDAO appDAO, String title) {
		Course course = appDAO.findCourseByName(title);

		System.out.println(course.toString());

		for (Student student : course.getStudents()) {
			System.out.println(student.toString());
		}
	}

	private void insertStudentIntoCourse(AppDAO appDAO, StudentDAO studentDAO) {
		Course course = appDAO.findCourseByName("CSS3");
		List<Student> students = studentDAO.findByLastName("King");
		Student student = students.getFirst();
		course.add(student);
		appDAO.update(course);
	}

	private void removeStudentFromCourse(AppDAO appDAO, StudentDAO studentDAO) {
		Course course = appDAO.findCourseByName("CSS3");
		Student student = studentDAO.findById(11);

		studentDAO.removeStudentFromCourse(student.getId(), course.getId());

		//course.remove(student);
		//student.remove(course);

		//appDAO.update(course);
		//studentDAO.update(student);
		//appDAO.remove(course);
		//studentDAO.remove(student);

	}

	private void renameCourse(AppDAO appDAO, String oldTitle, String newTitle) {
		Course course = appDAO.findCourseByName(oldTitle);
		course.setTitle(newTitle);
		appDAO.update(course);
	}

	private void queryForStudentByLastName(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findByLastName("Lavrencova");

		for (Student student : students) {
			System.out.println(student.toString());
			System.out.println("Courses študenta: ");
			List<Course> courses = student.getCourses();

			for (Course course : courses) {
				System.out.println(course.toString());
			}
		}
	}

	private void queryForCourses(AppDAO appDAO) {
		List<Course> courses = appDAO.getAllCourses();

		for (Course course : courses) {
			System.out.println(course.toString());
		}
	}

	private void addCourseToStudent(StudentDAO studentDAO, AppDAO appDAO, String courseName, String lastName) {
		Course course = appDAO.findCourseByName(courseName);
		List<Student> student = studentDAO.findByLastName(lastName);

		Student theStudent = student.getFirst();

		course.add(theStudent);
		appDAO.update(course);
	}
	private void addStudentToCourse(StudentDAO studentDAO, AppDAO appDAO, String courseName, String lastName) {
		Course course = appDAO.findCourseByName(courseName);
		List<Student> student = studentDAO.findByLastName(lastName);

		Student theStudent = student.getFirst();

		theStudent.addCourse(course);
		studentDAO.update(theStudent);
	}

	private void insertReview(AppDAO appDAO) {
		System.out.println("Inserted new review");
		Review review = new Review(id, "Toto je prvý komentár, ktorý bol kedy stvorený.", 1);
		appDAO.save(review);
	}

	private void queryForReviews(AppDAO appDAO) {
		System.out.println("Query for reviews by course id: ");
		List<Review> list = appDAO.getAllReviewsByCourseId(1);
		for (Review review : list) {
			System.out.println(review.toString());
		}
	}

	private void updateReview(AppDAO appDAO) {
		/*System.out.println("Updated review");
		Review review = new Review(id++, "Dám tomu ešte šancu", 1);
//		appDAO.save(review);*/
		Course tempCourse = appDAO.findCourseById(1);
		Review tempReview = tempCourse.getReview().get(id);
		tempReview.setComment("Dám tomu ešte šancu");
		//tempCourse.setReview(tempCourse.getReview().get(id));
	}



	private void queryForReviewsInCourseById(AppDAO appDAO) {
		Course course = appDAO.findById(2).getCourses().getFirst();
		List<Review> list = course.getReview();
		for (Review review : list) {
			System.out.println(review.toString());
		}
	}

	private void queryForInstructor(AppDAO appDAO) {
		int id = 2;
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
		List<Instructor> instructors = appDAO.findByEmail("%vol%");

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
		Instructor tempInstructor = new Instructor("Pavol", "Herman", "pavolh@gmail.com");
		InstructorDetail tempInstructorDetail = new InstructorDetail("TechnoHvězda","Hockey");
		tempInstructor.setInstructorDetail(tempInstructorDetail);
		appDAO.save(tempInstructor);
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor tempInstructor = new Instructor("Miro", "Válek", "valekm@seznam.cz");
		InstructorDetail detail = new InstructorDetail("http://youtobe/java", "reading books");
		tempInstructor.setInstructorDetail(detail);

		Course course1 = new Course("Python pre pokročilých");
		Course course2 = new Course("Managment");
		tempInstructor.add(course1);
		tempInstructor.add(course2);

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
		Student tempStudent = new Student("Xenia", "Lavrencova", "xenia@gmail.com");
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
