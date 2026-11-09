package com.spring.hibernate_demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

/**
 * InstructorDetail entity mapped to instructordetail table.
 */
@Entity 
public class InstructorDetail {

    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String subject;
    private String hobby;

    @OneToOne(mappedBy = "instructorDetail", cascade = CascadeType.ALL)
    private Instructor instructor;


    //Constructors
    public InstructorDetail() {}

    public InstructorDetail(String subject, String hobby) {
        this.subject = subject;
        this.hobby = hobby;
    }


    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
    
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }


    //Getters
    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getHobby() {
        return hobby;
    }

    public Instructor getInstructor() {
        return instructor;
    }


    @Override
    public String toString() {
        return 
            "InstructorDetail [id=" + id + 
            ", subject=" + subject + 
            ", hobby=" + hobby + "]";
    }
}
