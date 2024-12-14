package com.yusuf.cruddemo.dao;

import com.yusuf.cruddemo.entity.Course;
import com.yusuf.cruddemo.entity.Instructor;
import com.yusuf.cruddemo.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {

    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    Course findCourse(int theId);

    List<Course> findCoursesByInstructorId(int theId);

    Instructor findInstructorByIdJoinFetch(int theId);

    void updateInstructor(Instructor instructor);

    void updateCourse(Course course);

    void deleteInstructorWithAssociatedCourse(int theId);

    void deleteCourse(int theId);

    void save(Course course);

    Course findCourseAndReviewsById(int theId);
}
