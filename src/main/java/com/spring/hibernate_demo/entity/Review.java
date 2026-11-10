package com.spring.hibernate_demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Review {

    //Fields
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String comments;

    //Constructors
    public Review() {}

    public Review(String comments) {
        this.comments = comments;
    }
    

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    //Getters
    public int getId() {
        return id;
    }

    public String getComments() {
        return comments;
    }


    @Override
    public String toString() {
        return 
            "Review [id=" + id + 
            ", comments=" + comments + "]";
    }
}
