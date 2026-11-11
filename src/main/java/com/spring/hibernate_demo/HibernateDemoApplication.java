package com.spring.hibernate_demo;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.spring.hibernate_demo.dao.AppDao;
import com.spring.hibernate_demo.dao.StudentDao;
import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Review;
import com.spring.hibernate_demo.entity.Student;

/**
 * Main Spring Boot configuration HibernateDemoApplication.
 * Entry point for Hibernate Demo application.
 */
@SpringBootApplication
public class HibernateDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateDemoApplication.class, args);
	}

	/**
	 * Executes initial database operations.
	 * Immediately after ApplicationContext loads.
	 * 
	 * @param studentDao the injected component for database access.
	 * @param AppDao the injected component used in Instructor implementation.
	 * @return a CommandLineRunner instance
	 */
	// CommandLineRunner commandLineRunner(StudentDao studentDao) {
	@Bean
	CommandLineRunner commandLineRunner(AppDao app) {
		return runner -> {
			//== STUDENT entity ==
			// createStudent(studentDao);
			// readStudent(studentDao);
			// deleteStudent(studentDao);
			// deleteAllStudents(studentDao);
			// queryStudents(studentDao);
			// queryStudentLastName(studentDao);
			// updateStudent(studentDao);

			
			//== INSTRUCTOR entity mapping ==
			// createInstructor(app);
			// findInstructor(app);
			// deleteInstructor(app);
			// findInstructorDetail(app);
			// deleteInstructorDetail(app);
			// createInstructorWithCourse(app);
			// findInstructorWithCourse(app);
			// findCoursesForInstructor(app);
			// findInstructorWithCourseJoinFetch(app);
			// updateInstructor(app);
			// updateCourse(app);
			// deleteInstructorOnly(app);
			// deleteCourse(app);
			// createCourseAndReviews(app);
			// retrieveCourseAndReview(app);
			// deleteCourseAndReview(app);
			// createCourseAndStudents(app);
			// findCourseAndStudents(app);
			// findStudentAndCourse(app);
			// addMoreCourseForStudent(app);
			deleteStudent(app);
		};
	}

	// ==========================================
    // INSTRUCTOR MAPPING FUNCTIONS
    // ==========================================
	/**
	 * Deletes a student entity by its primary key ID.
	 *
	 * @param app the DAO component used for database operations.
	 */
	private void deleteStudent(AppDao app) {
		int id = 42;
		System.out.println("Delete student: " + id);

		app.deleteStudentById(id);
		System.out.println("Deleted successfully!");
	}

	/**
	 * Enrolls an existing student into additional courses.
	 * Persists the updated relationship.
	 *
	 * @param app the DAO component used for database operations
	 */
	private void addMoreCourseForStudent(AppDao app) {
		int id = 41;
		Student student = app.findStudentAndCourseByStudentId(id);

		Course course1 = new Course("Gaslighting");
		Course course2 = new Course("Criminilogy");
		Course course3 = new Course("The matter of life and death");

		student.addCourse(course1);
		student.addCourse(course2);
		student.addCourse(course3);

		System.out.println("Successfully enrolled new course!");

		app.update(student);
	}

	/**
	 * Retrieves a student with their courses using a join query.
	 *
	 * @param app the DAO component used for database operations
	 */
	private void findStudentAndCourse(AppDao app) {
		int id = 41;
		Student student = app.findStudentAndCourseByStudentId(id);

		System.out.println("Student: " + student);
		System.out.println("Courses: " + student.getCourses());
	}

	/**
	 * Retrieves a course along with its enrolled students.
	 *
	 * @param app the DAO component used for database operations
	 */
	private void findCourseAndStudents(AppDao app) {
		int id = 23;

		Course course = app.findCourseAndStudentByCourseId(id);

		System.out.println("Course: " + course);
		System.out.println("Students: " + course.getStudents());
	}

	/**
	 * Creates a new course with associated student entities.
	 * Saves newly created course to the database.
	 *
	 * @param app the DAO component used for database operations
	 */
	private void createCourseAndStudents(AppDao app) {
		Course course = new Course("World Domination");

		//create students
		Student student1 = new Student(
			"Light", 
			"Yagami",
			"light.yagami@email.com" 
		);
		Student student2 = new Student(
			"Gellert",
			"Grindlewald",
			"deathly.hallows@email.com"
		);

		course.addStudent(student1);
		course.addStudent(student2);

		app.save(course);
		System.out.println("Successfully enrolled");
	}

	/**
	 * Deletes a course entity along with its reviews by ID.
	 *
	 * @param app the DAO component used for database operations
	 */
	private void deleteCourseAndReview(AppDao app) {
		int id = 21;
		System.out.println("Delete course: " + id);

		app.deleteCourseById(id);
		System.out.println("Deleted successfully");
	}

	/**
	 * Retrieves a course and its reviews by course ID.
	 *
	 * @param app the DAO component used for database operations
	 */
	private void retrieveCourseAndReview(AppDao app) {
		int id = 21;
		System.out.println("Get course and reviews from id: " + id);

		Course course = app.findCourseAndReviewByCourseId(id);

		System.out.println(course);
		System.out.println(course.getReview());
	}

	/**
	 * Creates a new course along with new review entries.
	 * Persists newly created course to the database
	 *
	 * @param app the DAO component used for database operations
	 */
	private void createCourseAndReviews(AppDao app) {
		Course course = new Course("DSA");

		//add reviews
		course.addReview(new Review("Very hard to pass!"));
		course.addReview(new Review("I had fun!"));
		course.addReview(new Review(
			"DSA stands for Data Structure Algorithms"
		));

		System.out.println("Course saved!");
		app.save(course);
	}

	/**
	 * Deletes a course entity from the database by its ID.
	 *
	 * @param app the DAO component used for database operations
	 */
	private void deleteCourse(AppDao app) {
		int id = 24;
		System.out.println("Delete course: " + id);

		app.deleteCourseById(id);
		System.out.println("Deleted successfully");
	}

	/**
	 * Deletes an instructor entity by ID.
	 * Without deleting their associated courses.
	 *
	 * @param app the DAO component used for database operations
	 */
	private void deleteInstructorOnly(AppDao app) {
		int id = 6;
		System.out.println("Delete instructor: " + id);

		app.deleteInstructorOnlyById(id);
		System.out.println("Deleted successfully");
	}

	/**
	 * Updates the title of an existing course entity in the database.
	 *
	 * @param app the DAO component used for database operations
	 */
	private void updateCourse(AppDao app) {
		int id = 2;
		System.out.println("Find course id: " + id);

		Course tCourse = app.findCourseById(id);

		tCourse.setTitle("Mathematics");

		System.out.println("Updated");
		app.updateCourse(tCourse);
	}

	/**
	 * Updates an existing instructor entity in the database.
	 *
	 * @param app the DAO component used for database operations
	 */
	private void updateInstructor(AppDao app) {
		int id = 6;
		System.out.println("Find instructor id: " + id);

		Instructor ins = app.findInstructorById(id);

		ins.setLastName("Jao");

		System.out.println("Updated");
		app.update(ins);
	}

	/**
	 * Retrieves an instructor along with their courses in a single query.
	 * Uses JOIN FETCH to prevent lazy loading issues.
	 *
	 * @param app the DAO component used for database operations
	 */
	private void findInstructorWithCourseJoinFetch(AppDao app) {
		int id = 6;
		System.out.println("Find instructor id: " + id);

		Instructor ins = app.findInstructorByIdJoinFetch(id);

		System.out.println("Instructor: " + id);
		System.out.println("Courses: " + ins.getCourses());
	}

	/**
	 * Fetches an instructor and their associated courses.
	 * Via two separate DAO calls and links them manually.
	 *
	 * @param app the DAO component used for database operations
	 */
	private void findCoursesForInstructor(AppDao app) {
		int id = 6;

		//Find instructor
		System.out.println("Find instructor id: "+ id);
		Instructor ins = app.findInstructorById(id);
		System.out.println("Instructor: " + ins);

		//Get courses of instructor
		System.out.println("Courses from id: " + id);
		List<Course> courses = app.findCoursesByInstructorId(id);

		//associate the objects
		ins.setCourses(courses);
		System.out.println(ins.getCourses());
	}

	/**
	 * Retrieves an instructor by ID.
	 * Attempts to access lazy-loaded courses.
	 *
	 * @param app the DAO component used for database operations
	 */
	private void findInstructorWithCourse(AppDao app) {
		int id = 6;
		System.out.println("Find instructor id: "+ id);
		Instructor ins = app.findInstructorById(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Courses: " + ins.getCourses());
	}

	/**
	 * Creates a new instructor and assigned courses.
	 * Saves all entities.
	 *
	 * @param app the DAO component used for database operations
	 */
	private void createInstructorWithCourse(AppDao app) {
		Instructor ins = new Instructor(
			"Osamu", 
			"Dazai", 
			"nolongerhuman@email.com"
		);
		
		InstructorDetail deets = new InstructorDetail(
			"Gun Safety",
			"Double UnAliving"
		);

		ins.setInstructorDetail(deets);

		Course course1 = new Course("History");
		Course course2 = new Course("Politics");

		ins.add(course1);
		ins.add(course2);

		System.out.println("Courses saved: " + ins.getCourses());
		app.save(ins);
	}

	/**
	 * Deletes an instructor detail record by ID.
	 *
	 * @param app the DAO component used for database operations
	 */
	private void deleteInstructorDetail(AppDao app) {
		int id = 3;
		System.out.println("Delete id: " + id);

		app.deleteInstructorDetail(id);
		System.out.println("Deleted successfully!");
	}

	/**
	 * Retrieves an instructor detail record.
	 * Along with its associated instructor entity.
	 *
	 * @param app the DAO component used for database operations
	 */
	private void findInstructorDetail(AppDao app) {
		int id = 2;
		InstructorDetail deets = app.findInstructorDetailById(id);

		System.out.println("Details: " + deets);
		System.out.println("Instructor: " + deets.getInstructor());
	}

	/**
	 * Deletes an instructor entity and cascading entities by ID.
	 *
	 * @param app the DAO component used for database operations
	 */
	private void deleteInstructor(AppDao app) {
		int id = 1;
		System.out.println("Delete id: " + id);

		app.deleteInstructorById(id);
		System.out.println("Deleted successfully!");
	}

	/**
	 * Retrieves an instructor entity and its associated details by ID.
	 *
	 * @param app the DAO component used for database operations
	 */
	private void findInstructor(AppDao app) {
		int id = 1;
		System.out.println("Find id: " + id);

		Instructor ins = app.findInstructorById(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Details: " + ins.getInstructorDetail());
	}

	/**
	 * Creates a single instructor with details.
	 * Persists newly created to the database.
	 *
	 * @param app the DAO component used for database operations
	 */
	private void createInstructor(AppDao app) {
		System.out.println("Creating instructor information...");

		Instructor ins = new Instructor(
			"Robin",
			"Nico",
			"nico.robin@email.com"
		);

		InstructorDetail deets = new InstructorDetail(
			"Literature",
			"Reading books"
		);

		ins.setInstructorDetail(deets);

		System.out.println("Information saved!");
		app.save(ins);
	}


	// ==========================================
    // STUDENT MAPPING FUNCTIONS
    // ==========================================
	/**
	 * Deletes all student records from the database.
	 * Prints the total row count deleted.
	 *
	 * @param studentDao the DAO component used for database operations
	 */
	private void deleteAllStudents(StudentDao studentDao) {
		System.out.println("Deleting all student information...");
		int rowsDeleted = studentDao.deleteAll();
		System.out.println("Number of rows deleted: " + rowsDeleted);
	}

	/**
	 * Deletes a single student entity by ID.
	 *
	 * @param studentDao the DAO component used for database operations
	 */
	private void deleteStudent(StudentDao studentDao) {
		//retrieve by specific id
		int id = 1;
		System.out.println("Delete ID: " + id);

		studentDao.delete(id);
	}

	/**
	 * Retrieves a student entity by ID.
	 * Modifies and saves the updated record.
	 *
	 * @param studentDao the DAO component used for database operations
	 */
	private void updateStudent(StudentDao studentDao) {
		//retrieve by specific id
		int id = 1;
		System.out.println("Student ID: " + id);
		Student student = studentDao.findById(id);

		//change first name
		System.out.println("Updating student name...");
		student.setFirstName("Sherly");

		//update
		studentDao.update(student);
		System.out.println("Updated student information: " + student);
	}

	/**
	 * Queries all students from the database that matches lastName.
	 * Prints query.
	 *
	 * @param studentDao the DAO component used for database operations
	 */
	private void queryStudentLastName(StudentDao studentDao) {
		List<Student> students = studentDao.findByLastName("Ushijima");

		//display
		for(Student s:students) {
			System.out.println(s);
		}
	}

	/**
	 * Dsiplays all student records present in the database.
	 *
	 * @param studentDao the DAO component used for database operations
	 */
	private void queryStudents(StudentDao studentDao) {
		//get list of all students
		List<Student> students = studentDao.findAll();

		//display
		for(Student s:students) {
			System.out.println(s);
		}
	}

	/**
	 * Retrieves and prints details of a specific student by ID.
	 *
	 * @param studentDao the DAO component used for database operations
	 */
	private void readStudent(StudentDao studentDao) {
		//retrieve based on id
		System.out.println("Retrieving student id...");
		Student student = studentDao.findById(2);

		System.out.println("Found the student " + student);
	}

	/**
	 * Creates new Student entities.
	 * Saves newly created entity to the database. 
	 *
	 * @param studentDao the DAO component used for database operations
	 */
	private void createStudent(StudentDao studentDao) {
		
		//Creating student
		System.out.println("Creating student information...");
		Student student1 = new Student(
			"Wakatoshi",
			"Ushijima",
			"ushiwaka@email.com"
		);

		Student student2 = new Student(
			"Zoro",
			"Roronoa",
			"three.swords@email.com"
		);

		Student student3 = new Student(
			"Sherly",
			"Ushijima",
			"ushiwaka@email.com"
		);

		//Saving student
		System.out.println("Saving student information...");
		studentDao.save(student1);
		studentDao.save(student2);
		studentDao.save(student3);

		//display student id for confirmation
		System.out.println("Saved! Generated id: " + student1.getId());
		System.out.println("Saved! Generated id: " + student2.getId());
		System.out.println("Saved! Generated id: " + student3.getId());
	}

}
