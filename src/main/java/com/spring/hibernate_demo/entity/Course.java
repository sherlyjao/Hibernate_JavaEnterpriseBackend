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
}
