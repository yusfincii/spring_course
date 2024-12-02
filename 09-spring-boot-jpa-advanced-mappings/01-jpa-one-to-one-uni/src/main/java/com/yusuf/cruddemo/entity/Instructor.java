package com.yusuf.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private int first_name;

    @Column(name = "last_name")
    private int last_name;

    @Column(name = "email")
    private int email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    @Column(name = "instructor_detail_id")
    private InstructorDetail instructor_detail_id;

    public Instructor(){}

    public Instructor(int first_name, int last_name, int email){
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", first_name=" + first_name +
                ", last_name=" + last_name +
                ", email=" + email +
                ", instructor_detail_id=" + instructor_detail_id +
                '}';
    }
}
