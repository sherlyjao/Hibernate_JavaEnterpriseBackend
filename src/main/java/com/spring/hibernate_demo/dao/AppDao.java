package com.spring.hibernate_demo.dao;

import java.util.List;

import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;

/**
 * Data Access Object interface AppDao.
 */
public interface AppDao {

    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetail(int theId);
    List<Course> findCoursesByInstructorId(int theId);
}
