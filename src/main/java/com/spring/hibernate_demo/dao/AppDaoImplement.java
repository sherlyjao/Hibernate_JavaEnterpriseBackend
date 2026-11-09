package com.spring.hibernate_demo.dao;

import org.springframework.stereotype.Repository;

import com.spring.hibernate_demo.entity.Instructor;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

/**
 * JPA implementation StudentDaoImplement of {@link AppDao}.
 * Uses {@link EntityManager}.
 */
@Repository 
public class AppDaoImplement implements AppDao {

    private EntityManager em;

    public AppDaoImplement(EntityManager em) {
        this.em =  em;
    }

    @Override 
    @Transactional
    public void save(Instructor theInstructor) {
        em.persist(theInstructor);
    }
}
