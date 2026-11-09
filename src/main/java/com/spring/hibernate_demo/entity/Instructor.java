package com.spring.hibernate_demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity 
public class Instructor {

    //Fields
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;

    @OneToMany(
        mappedBy = "instructor", 
        fetch = FetchType.LAZY,
        cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
        }
    )
    @Column
    private List<Course> courses;


    //Constructors
    public Instructor() {}

    public Instructor(
        String firstName, 
        String lastName, 
        String email
    ) {
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

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
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
    
    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }
    
    public List<Course> getCourses() {
        return courses;
    }


    @Override
    public String toString() {
        return 
            "Instructor [id=" + id + 
            ", firstName=" + firstName + 
            ", lastName=" + lastName + 
            ", email=" + email + "]";
    }


    public void add(Course tCourse) {
        //initialize if null
        if (this.courses == null) {
            this.courses = new  ArrayList<>();
        }

        this.courses.add(tCourse);
        tCourse.setInstructor(this);
    }


}
