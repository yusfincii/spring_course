package com.yusuf.cruddemo.dao;

import com.yusuf.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {

    void save(Student theStudent);

    Student findById(Integer id);

    List<Student> findAll();

    List<Student> findByName(String name);

    void update(Student theStudent);

    void delete(Integer id);

    int deleteAll();
}
