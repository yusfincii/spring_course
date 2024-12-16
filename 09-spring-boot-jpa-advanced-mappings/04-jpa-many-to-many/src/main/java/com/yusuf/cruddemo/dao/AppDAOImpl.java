package com.yusuf.cruddemo.dao;

import com.yusuf.cruddemo.entity.Course;
import com.yusuf.cruddemo.entity.Instructor;
import com.yusuf.cruddemo.entity.InstructorDetail;
import com.yusuf.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Table;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    public Course findCourse(int theId){
        return entityManager.find(Course.class, theId);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course WHERE instructor.id=:data", Course.class);
        query.setParameter("data", theId);

        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        entityManager.remove(findInstructorById(theId));
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        // firstly remove the associated object reference
        // break bidirectional link
        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        // find and remove
        entityManager.remove(entityManager.find(InstructorDetail.class, theId));
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId){
        TypedQuery<Instructor> query = entityManager.createQuery("SELECT i from Instructor as i "+
                "JOIN FETCH i.courses WHERE i.id=:data", Instructor.class);
        query.setParameter("data", theId);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void updateCourse(Course course){
        entityManager.merge(course);
    }

    @Override
    @Transactional
    public void deleteInstructorWithAssociatedCourse(int theId) {
        Instructor instructor = findInstructorByIdJoinFetch(theId);
        List<Course> associatedCourses = instructor.getCourses();
        for(Course course : associatedCourses){
            course.setInstructor(null);
        }
        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void deleteCourse(int theId) {
        entityManager.remove(findCourse(theId));
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsById(int theId) {
        TypedQuery<Course> query = entityManager.createQuery("from Course as c " +
                "join fetch c.reviews where c.id=:data", Course.class);
        query.setParameter("data", theId);
        return query.getSingleResult();
    }

    @Override
    public Course findCourseAndStudentsById(int theId) {
        TypedQuery<Course> query = entityManager.createQuery("from Course as c " +
                "join fetch c.students where c.id=:data", Course.class);
        query.setParameter("data", theId);
        return query.getSingleResult();
    }

    @Override
    public Student findStudentAndCourseById(int theId) {
        TypedQuery<Student> query = entityManager.createQuery("from Student as s " +
                "join fetch s.courses where s.id=:data", Student.class);
        query.setParameter("data", theId);
        return query.getSingleResult();

    }

    @Override
    @Transactional
    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {
        // following method can be suitable because if cascade types modified in classes
        Student student = findStudentAndCourseById(theId);
        // Student student = entityManager.find(Student.class, theId); // OPTIONAL
        entityManager.remove(student);

    }
}
