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

    void save(Instructor theInstructor);
    void save(Course theCourse);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetail(int theId);
    List<Course> findCoursesByInstructorId(int theId);
    Instructor findInstructorByIdJoinFetch(int theId);
    void update(Instructor in);
    void update(Student student);
    void updateCourse(Course tCourse);
    Course findCourseById(int theId);
    void deleteInstructorOnlyById(int theId);
    void deleteCourseById(int theId);
    Course findCourseAndReviewByCourseId(int theId);
    Course findCourseAndStudentByCourseId(int theId);
    Student findStudentAndCourseByStudentId(int theId);
    void deleteStudentById(int theId);
}
