package com.spring.hibernate_demo.dao;

import com.spring.hibernate_demo.entity.Instructor;

/**
 * Data Access Object interface AppDao.
 */
public interface AppDao {

    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
}
