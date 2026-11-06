package com.spring.hibernate_demo.dao;

import java.util.List;

import com.spring.hibernate_demo.entity.Student;

public interface StudentDao {

    void save(Student theStudent);
    Student findById(Integer Id);
    List<Student> findAll();
}
