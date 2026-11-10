package com.spring.hibernate_demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
// import jakarta.persistence.Column;
// import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

/**
 * The Student entity mapped to 'student' table in Oracle.
 */
@Entity 
// @Table
public class Student {

    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column
    private int id;

    // @Column
    private String firstName;

    // @Column
    private String lastName;

    // @Column
    private String email;

    @ManyToMany (
        mappedBy = "students",
        fetch = FetchType.LAZY,
        cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
        }
    )
    private List<Course> courses;


    
    //Constructors
    public Student() {
    }
    
    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    
    
    //Setters
    public void setId(int id) {
        this.id = id;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }


    //Getters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public List<Course> getCourses() {
        return courses;
    }
    

    @Override 
    public String toString() {
        return 
            "Student [id=" + id +
            ", firstName=" + firstName +
            ", lastName=" + lastName +
            ", email=" + email + "]";
    }

    public void addCourse(Course course) {
        if (courses == null) {
            courses = new ArrayList<>();
        }

        courses.add(course);
        course.addStudent(this);
    }
}
