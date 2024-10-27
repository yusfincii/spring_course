package com.yusuf.demo.rest;

import com.yusuf.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    List<Student> studentList;

    @PostConstruct
    public void loadData(){
        studentList = new ArrayList<>();

        studentList.add(new Student("Yusuf", "INCI"));
        studentList.add(new Student("Ahmet Burak", "DINC"));
        studentList.add(new Student("Lana", "Rhoades"));
    }

    // define endpoint for "/students" - returns a list of all students
    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studentList;
    }

    // define endpoint for "/student/{studentId}" - returns a student with given id
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        if( (studentId >= studentList.size()) || (studentId < 0)){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }

        return studentList.get(studentId);
    }


}
