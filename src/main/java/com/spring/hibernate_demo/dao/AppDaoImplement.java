package com.spring.hibernate_demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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

    @Override
    public Instructor findInstructorById(int theId) {
        return em.find(Instructor.class, theId);
    }

    @Override 
    @Transactional
    public void deleteInstructorById(int theId) {
        em.remove(findInstructorById(theId));
    }

    @Override 
    public InstructorDetail findInstructorDetailById(int theId) {
        return em.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetail(int theId) {
        InstructorDetail deets = findInstructorDetailById(theId);

        deets.getInstructor().setInstructorDetail(null);
        em.remove(deets);
    }
    
    @Override 
    public List<Course> findCoursesByInstructorId(int theId) {
        TypedQuery<Course> query = em.createQuery(
            "From Course WHERE instructor.id=:data",
            Course.class
        );

        query.setParameter("data", theId);
        List<Course> courses = query.getResultList();
        return courses;
    }

}
