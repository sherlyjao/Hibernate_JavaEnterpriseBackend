package com.spring.hibernate_demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.hibernate_demo.dao.StudentDao;
import com.spring.hibernate_demo.entity.Student;

@SpringBootApplication
public class HibernateDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentDao studentDao) {
		return runner -> {
			createStudent(studentDao);
			// readStudent(studentDao);
			// queryStudents(studentDao);
			queryStudentLastName(studentDao);
		};
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
