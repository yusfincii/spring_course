package com.yusuf.cruddemo.dao;

import com.yusuf.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{
    // define field for entity manager
    private final EntityManager entityManager;

    // inject entity manager using constructor injection
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    // find student by given ID
    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    // retrieve all students
    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student order by firstName desc ", Student.class);
        return query.getResultList();
    }

    // retrieve students by given name
    @Override
    public List<Student> findByName(String name) {
        TypedQuery<Student> students = entityManager.createQuery("FROM Student WHERE firstName=:data", Student.class);
        students.setParameter("data", name);
        return students.getResultList();
    }

    // update
    @Transactional
    @Override
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Student theStudent = entityManager.find(Student.class, id); // retrieve the student
        //Student theStudent = findById(id); // optional method
        entityManager.remove(theStudent);
    }

    @Transactional
    @Override
    public int deleteAll() {
        int numberOfRowsDeleted = entityManager.createQuery("delete from Student").executeUpdate();
        // executeUpdate() --> a function to know database has changed
        return numberOfRowsDeleted;
    }


}
