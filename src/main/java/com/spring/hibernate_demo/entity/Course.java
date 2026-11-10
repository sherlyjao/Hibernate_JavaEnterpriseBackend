package com.spring.hibernate_demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Course {

    //Fields
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @ManyToOne(cascade = {
        CascadeType.DETACH,
        CascadeType.MERGE,
        CascadeType.PERSIST,
        CascadeType.REFRESH
    })
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL
    )
    @JoinColumn (name = "course_id")
    private List<Review> review;

    @ManyToMany (
        fetch = FetchType.LAZY,
        cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
        }
    )
    @JoinTable (
        name = "course_student",
        joinColumns = @JoinColumn (
            name = "course_id"
        ),
        inverseJoinColumns = @JoinColumn (
            name = "student_id"
        )
    )
    private List<Student> students;

    
    
    //Constructors
    public Course() {
    }
    
    public Course(String title) {
        this.title = title;
    }
    
    
    //Setters
    public void setId(int id) {
        this.id = id;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    
    public void setReview(List<Review> review) {
        this.review = review;
    }
    
    public void setStudents(List<Student> students) {
        this.students = students;
    }
    
    //Getters
    public int getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public Instructor getInstructor() {
        return instructor;
    }
    
    public List<Review> getReview() {
        return review;
    }
    
    public List<Student> getStudents() {
        return students;
    }
    

    @Override
    public String toString() {
        return 
        "Course [id=" + id + 
        ", title=" + title + "]";
    }
    
    public void addReview(Review rev) {
        if (review == null) {
            review = new ArrayList<>();
        }
        
        review.add(rev);
    }
    
    public void addStudent(Student stud) {
        if (students == null) {
            students = new ArrayList<>();
        }

        students.add(stud);
    }
}
