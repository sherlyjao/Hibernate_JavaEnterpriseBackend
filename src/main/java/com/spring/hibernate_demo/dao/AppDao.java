package com.spring.hibernate_demo.dao;

import java.util.List;

import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Student;

/**
 * Data Access Object interface AppDao.
 */
public interface AppDao {

    // ==========================================
    // Save Operations
    // ==========================================

    /**
     * Persists a new {@link Instructor} object into the database.
     *
     * @param theInstructor the {@link Instructor} entity to be saved.
     */
    void save(Instructor theInstructor);

    /**
     * Persists a new {@link Course} object into the database.
     *
     * @param theCourse the {@link Course} entity to be saved.
     */
    void save(Course theCourse);


    // ==========================================
    // Find Operations
    // ==========================================

    /**
     * Finds an {@link Instructor} by its primary key ID.
     *
     * @param theId the unique identifier of the instructor.
     * @return the matching {@link Instructor} entity,
     * or {@code null} if not found.
     */
    Instructor findInstructorById(int theId);

    /**
     * Finds an {@link InstructorDetail} entity by its primary key ID.
     *
     * @param theId the unique identifier of the instructor detail.
     * @return the matching {@link InstructorDetail} entity,
     * or {@code null} if not found.
     */
    InstructorDetail findInstructorDetailById(int theId);

    /**
     * Retrieves an {@link Instructor} along with associated {@link Course}.
     * in a single query using JPQL JOIN FETCH to avoid lazy loading issues.
     *
     * @param theId the unique identifier of the instructor
     * @return the {@link Instructor} entity with eagerly loaded courses
     */
    Instructor findInstructorByIdJoinFetch(int theId);

    /**
     * Finds all {@link Course} objects associated with a specific ID.
     *
     * @param theId the unique identifier of the instructor
     * @return a {@link List} of {@link Course} objects.
     */
    List<Course> findCoursesByInstructorId(int theId);

    /**
     * Finds a {@link Course} by its primary key ID.
     *
     * @param theId the unique identifier of the course.
     * @return the matching {@link Course} entity,
     * or {@code null} if not found.
     */
    Course findCourseById(int theId);

    /**
     * Retrieves a {@link Course} along with its reviews in a single query.
     *
     * @param theId the unique identifier of the course
     * @return the matching {@link Course} entity with loaded reviews
     */
    Course findCourseAndReviewByCourseId(int theId);

    /**
     * Retrieves a {@link Course} along with its enrolled {@link Student}.
     *
     * @param theId the unique identifier of the course
     * @return the matching {@link Course} entity with loaded students
     */
    Course findCourseAndStudentByCourseId(int theId);

    /**
     * Retrieves a {@link Student} along with their enrolled {@link Course}.
     *
     * @param theId the unique identifier of the student
     * @return the matching {@link Student} entity with loaded courses
     */
    Student findStudentAndCourseByStudentId(int theId);
    

    // ==========================================
    // Delete Operations
    // ==========================================
    
    /**
     * Deletes an {@link Instructor} and its associated entities by ID.
     *
     * @param theId the unique identifier of the instructor to delete.
     */
    void deleteInstructorById(int theId);

    /**
     * Deletes an {@link InstructorDetail} by primary key ID.
     *
     * @param theId the unique identifier of the instructor detail to delete.
     */
    void deleteInstructorDetail(int theId);

    /**
     * Deletes an {@link Instructor} by ID without deleting their courses,
     * unlinking the relationship to prevent foreign key violations.
     *
     * @param theId the unique identifier of the instructor to delete.
     */
    void deleteInstructorOnlyById(int theId);

    /**
     * Deletes a {@link Course} by its primary key ID.
     *
     * @param theId the unique identifier of the course to delete
     */
    void deleteCourseById(int theId);

    /**
     * Deletes a {@link Student} by their ID without affecting courses.
     *
     * @param theId the unique identifier of the student to delete
     */
    void deleteStudentById(int theId);
    

    // ==========================================
    // Update Operations
    // ==========================================

    /**
     * Updates an existing {@link Instructor} entity in the database.
     *
     * @param in the {@link Instructor} entity containing updated details
     */
    void update(Instructor in);

    /**
     * Updates an existing {@link Student} entity in the database.
     *
     * @param student the {@link Student} entity containing updated details
     */
    void update(Student student);

    /**
     * Updates an existing {@link Course} entity in the database.
     *
     * @param tCourse the {@link Course} entity containing updated details
     */
    void updateCourse(Course tCourse);
}
