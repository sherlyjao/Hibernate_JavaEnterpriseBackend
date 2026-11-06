package com.spring.hibernate_demo.dao;

import java.util.List;
import com.spring.hibernate_demo.entity.Student;

/**
 * Data Access Object interface StudentDao.
 * For performing CRUD operationson {@link Student} entity.
 */
public interface StudentDao {

    /**
     * Persists a new student record into database.
     * 
     * @param theStudent the entity to save.
     */
    void save(Student theStudent);

    /**
     * Retrieves student by unique id.
     * 
     * @param Id primary key of student.
     * @return the student entity found, null if not found.
     */
    Student findById(Integer Id);

    /**
     * Retrieves all students by order of lastName.
     * 
     * @return a List of all student entities.
     */
    List<Student> findAll();

    /**
     * Retrieves student with matching lastName with parameter.
     * 
     * @param lastName the lastName to be searched.
     * @return a List of matching Student entities.
     */
    List<Student> findByLastName(String lastName);

    /**
     * Updates an existing record in database.
     * 
     * @param theStudent the Student entity containing updated fields.
     */
    void update(Student theStudent);

    /**
     * Removes a specific student from database by Id.
     * 
     * @param id the primary key of student to delete.
     */
    void delete(Integer id);

    /**
     * Deletes all records from database table.
     * 
     * @return the number of rows deleted.
     */
    int deleteAll();
}
