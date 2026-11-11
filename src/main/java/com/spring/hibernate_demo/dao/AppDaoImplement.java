package com.spring.hibernate_demo.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Student;
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


    // ==========================================
    // Save Operations
    // ==========================================
    @Override 
    @Transactional
    public void save(Instructor theInstructor) {
        em.persist(theInstructor);
    }

    @Override 
    @Transactional 
    public void save(Course theCourse) {
        em.persist(theCourse);
    }


    // ==========================================
    // Find Operations
    // ==========================================
    @Override
    public Instructor findInstructorById(int theId) {
        return em.find(Instructor.class, theId);
    }

    @Override 
    public InstructorDetail findInstructorDetailById(int theId) {
        return em.find(InstructorDetail.class, theId);
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

    @Override 
    public Instructor findInstructorByIdJoinFetch(int theId) {
        TypedQuery<Instructor> query = em.createQuery(
            "SELECT i from Instructor i " + 
            "Join FETCH i.courses " + 
            "Join FETCH i.instructorDetail " +
            "WHERE i.id=:data",
            Instructor.class
        );
        query.setParameter("data", theId);

        Instructor in = query.getSingleResult();
        return in;
    }

    @Override
    public Course findCourseById(int theId) {
        return em.find(Course.class, theId);
    }

    @Override 
    public Course findCourseAndReviewByCourseId(int theId) {
        TypedQuery<Course> query = em.createQuery(
            "SELECT c FROM Course c " +
            "JOIN FETCH c.review " + 
            "WHERE c.id=:data", Course.class
        );
        query.setParameter("data", theId);

        Course course = query.getSingleResult();

        return course;
    }

    @Override 
    public Course findCourseAndStudentByCourseId(int theId) {
        TypedQuery<Course> query = em.createQuery(
            "SELECT c FROM Course c " +
            "JOIN FETCH c.students " +
            "WHERE c.id=:data", Course.class
        );
        query.setParameter("data", theId);

        Course course = query.getSingleResult();

        return course;
    }

    @Override 
    public Student findStudentAndCourseByStudentId(int theId) {
        TypedQuery<Student> query = em.createQuery(
            "SELECT s FROM Student s " +
            "JOIN FETCH s.courses " +
            "WHERE s.id=:data", Student.class
        );
        query.setParameter("data", theId);

        Student student = query.getSingleResult();

        return student;
    }


    // ==========================================
    // Delete Operations
    // ==========================================
    @Override 
    @Transactional
    public void deleteInstructorById(int theId) {
        em.remove(findInstructorById(theId));
    }

    @Override
    @Transactional
    public void deleteInstructorDetail(int theId) {
        InstructorDetail deets = findInstructorDetailById(theId);

        deets.getInstructor().setInstructorDetail(null);
        em.remove(deets);
    }

    @Override 
    @Transactional 
    public void deleteInstructorOnlyById(int theId) {
        Instructor in = findInstructorById(theId);
        List<Course> courses = in.getCourses();
        
        for (Course course: courses) {
            course.setInstructor(null);
        }

        em.remove(in);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        em.remove(findCourseById(theId));
    }

    @Override 
    @Transactional 
    public void deleteStudentById(int theId) {
        Student student = em.find(Student.class, theId);

        if (student != null) {
            List<Course> courses = student.getCourses();

            for (Course course: courses) {
                course.getStudents().remove(student);
            }
        }

        em.remove(student);
    }
    

    // ==========================================
    // Update Operations
    // ==========================================
    @Override
    @Transactional
    public void update(Instructor in) {
        em.merge(in);
    }

    @Override 
    @Transactional 
    public void update(Student student) {
        em.merge(student);
    }
    
    @Override 
    @Transactional 
    public void updateCourse(Course tCourse) {
        em.merge(tCourse);
    }
}
