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
			//== STUDENT entity
			// createStudent(studentDao);
			// readStudent(studentDao);
			// deleteStudent(studentDao);
			// deleteAllStudents(studentDao);
			// queryStudents(studentDao);
			// queryStudentLastName(studentDao);
			// updateStudent(studentDao);

			//== INSTRUCTOR entity mapping
			// createInstructor(app);
			// findInstructor(app);
			// deleteInstructor(app);
			// findInstructorDetail(app);
			// deleteInstructorDetail(app);
			// createInstructorWithCourse(app);
			// findInstructorWithCourse(app);
			findCoursesForInstructor(app);
		};
	}

	//== INSTRUCTOR entity mapping functions
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

	private void findInstructorWithCourse(AppDao app) {
		int id = 6;
		System.out.println("Find instructor id: "+ id);
		Instructor ins = app.findInstructorById(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Courses: " + ins.getCourses());
	}

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

	private void deleteInstructorDetail(AppDao app) {
		int id = 3;
		System.out.println("Delete id: " + id);

		app.deleteInstructorDetail(id);
		System.out.println("Deleted successfully!");
	}

	private void findInstructorDetail(AppDao app) {
		int id = 2;
		InstructorDetail deets = app.findInstructorDetailById(id);

		System.out.println("Details: " + deets);
		System.out.println("Instructor: " + deets.getInstructor());
	}

	private void deleteInstructor(AppDao app) {
		int id = 1;
		System.out.println("Delete id: " + id);

		app.deleteInstructorById(id);
		System.out.println("Deleted successfully!");
	}

	private void findInstructor(AppDao app) {
		int id = 1;
		System.out.println("Find id: " + id);

		Instructor ins = app.findInstructorById(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Details: " + ins.getInstructorDetail());
	}

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


	//== STUDENT entity functions
	// private void deleteAllStudents(StudentDao studentDao) {
	// 	System.out.println("Deleting all student information...");
	// 	int rowsDeleted = studentDao.deleteAll();
	// 	System.out.println("Number of rows deleted: " + rowsDeleted);
	// }

	// private void deleteStudent(StudentDao studentDao) {
	// 	//retrieve by specific id
	// 	int id = 1;
	// 	System.out.println("Delete ID: " + id);

	// 	studentDao.delete(id);
	// }

	// private void updateStudent(StudentDao studentDao) {
	// 	//retrieve by specific id
	// 	int id = 1;
	// 	System.out.println("Student ID: " + id);
	// 	Student student = studentDao.findById(id);

	// 	//change first name
	// 	System.out.println("Updating student name...");
	// 	student.setFirstName("Sherly");

	// 	//update
	// 	studentDao.update(student);
	// 	System.out.println("Updated student information: " + student);
	// }

	// private void queryStudentLastName(StudentDao studentDao) {
	// 	List<Student> students = studentDao.findByLastName("Ushijima");

	// 	//display
	// 	for(Student s:students) {
	// 		System.out.println(s);
	// 	}
	// }

	// private void queryStudents(StudentDao studentDao) {
	// 	//get list of all students
	// 	List<Student> students = studentDao.findAll();

	// 	//display
	// 	for(Student s:students) {
	// 		System.out.println(s);
	// 	}
	// }

	// private void readStudent(StudentDao studentDao) {
	// 	//retrieve based on id
	// 	System.out.println("Retrieving student id...");
	// 	Student student = studentDao.findById(2);

	// 	System.out.println("Found the student " + student);
	// }

	// private void createStudent(StudentDao studentDao) {
		
	// 	//Creating student
	// 	System.out.println("Creating student information...");
	// 	Student student1 = new Student(
	// 		"Wakatoshi",
	// 		"Ushijima",
	// 		"ushiwaka@email.com"
	// 	);

	// 	Student student2 = new Student(
	// 		"Zoro",
	// 		"Roronoa",
	// 		"three.swords@email.com"
	// 	);

	// 	Student student3 = new Student(
	// 		"Sherly",
	// 		"Ushijima",
	// 		"ushiwaka@email.com"
	// 	);

	// 	//Saving student
	// 	System.out.println("Saving student information...");
	// 	studentDao.save(student1);
	// 	studentDao.save(student2);
	// 	studentDao.save(student3);

	// 	//display student id for confirmation
	// 	System.out.println("Saved! Generated id: " + student1.getId());
	// 	System.out.println("Saved! Generated id: " + student2.getId());
	// 	System.out.println("Saved! Generated id: " + student3.getId());
	// }

}
