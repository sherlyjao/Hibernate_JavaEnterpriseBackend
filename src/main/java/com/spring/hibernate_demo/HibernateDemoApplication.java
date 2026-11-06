package com.spring.hibernate_demo;

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
		};
	}

	private void createStudent(StudentDao studentDao) {
		
		//Creating student
		System.out.println("Creating student information...");
		Student student1 = new Student(
			"Ana",
			"Alimurung",
			"ana.alimurung@email.com"
		);

		Student student2 = new Student(
			"Bambi",
			"Bascug",
			"bambi.bascug@email.com"
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
