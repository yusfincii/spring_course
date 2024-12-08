package com.yusuf.cruddemo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.sql.results.graph.collection.internal.EagerCollectionFetch;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.cdi.Eager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinWorkerThread;

@Entity
@Table(name = "instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;

    // following exist "mapped by" property and its value
    // refers to 'instructor' property in 'course' class
    @OneToMany(mappedBy = "instructor",
               //fetch = FetchType.EAGER,
               cascade = {CascadeType.DETACH, CascadeType.PERSIST,
                          CascadeType.MERGE, CascadeType.REFRESH})

    private List<Course> courses;

    public void add (Course tempCourse){
        if(courses == null){
            courses = new ArrayList<>();
        }
        courses.add(tempCourse);
        tempCourse.setInstructor(this);

    }

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    public Instructor(){}

    public Instructor(String first_name, String last_name, String email){
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> course) {
        this.courses = course;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", first_name=" + first_name +
                ", last_name=" + last_name +
                ", email=" + email +
                ", instructor_detail_id=" + instructorDetail +
                '}';
    }
}
