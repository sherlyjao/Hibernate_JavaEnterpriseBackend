package com.spring.hibernate_demo;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.spring.hibernate_demo.dao.StudentDao;
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
	 * Executes initial database operations immediately after ApplicationContext loads.
	 * 
	 * @param studentDao the injected component for database access.
	 * @return a CommandLineRunner instance
	 */
	@Bean
	CommandLineRunner commandLineRunner(StudentDao studentDao) {
		return runner -> {
			createStudent(studentDao);
			// readStudent(studentDao);
			// deleteStudent(studentDao);
			// deleteAllStudents(studentDao);
			// queryStudents(studentDao);
			// queryStudentLastName(studentDao);
			// updateStudent(studentDao);
		};
	}

	private void deleteAllStudents(StudentDao studentDao) {
		System.out.println("Deleting all student information...");
		int rowsDeleted = studentDao.deleteAll();
		System.out.println("Number of rows deleted: " + rowsDeleted);
	}

	private void deleteStudent(StudentDao studentDao) {
		//retrieve by specific id
		int id = 1;
		System.out.println("Delete ID: " + id);

		studentDao.delete(id);
	}

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

	private void queryStudentLastName(StudentDao studentDao) {
		List<Student> students = studentDao.findByLastName("Ushijima");

		//display
		for(Student s:students) {
			System.out.println(s);
		}
	}

	private void queryStudents(StudentDao studentDao) {
		//get list of all students
		List<Student> students = studentDao.findAll();

		//display
		for(Student s:students) {
			System.out.println(s);
		}
	}

	private void readStudent(StudentDao studentDao) {
		//retrieve based on id
		System.out.println("Retrieving student id...");
		Student student = studentDao.findById(2);

		System.out.println("Found the student " + student);
	}

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

		//Saving student
		System.out.println("Saving student information...");
		studentDao.save(student1);
		studentDao.save(student2);

		//display student id for confirmation
		System.out.println("Saved! Generated id: " + student1.getId());
		System.out.println("Saved! Generated id: " + student2.getId());
	}

}
