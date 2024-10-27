package com.yusuf.cruddemo;

import com.yusuf.cruddemo.dao.StudentDAO;
import com.yusuf.cruddemo.dao.StudentDAOImpl;
import com.yusuf.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			createMultipleStudents(studentDAO);
		};
	}

	private void createStudent(StudentDAO studentDAO) {
		// create the student object
		System.out.println("Creating student...");
		Student student1 = new Student("Yusuf", "INCI", "ysfnc27@gmail.com");

		// save the student object
		System.out.println("Saving student...");
		studentDAO.save(student1);

		// display id of the saved student
		System.out.println("Saved student. Generated ID: " + student1.getId());
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		// create the student objects
		System.out.println("Creating students...");
		Student student1 = new Student("Yusuf", "INCI", "ysfnc27@gmail.com");
		Student student2 = new Student("Yusuf2", "INCI", "ysfnc27@gmail.com");
		Student student3 = new Student("Yusuf3", "INCI", "ysfnc27@gmail.com");

		// save the student objects
		System.out.println("Saving students...");
		studentDAO.save(student1);
		studentDAO.save(student2);
		studentDAO.save(student3);

		// display id of the saved students
		System.out.println("Saved student. Generated ID: " + student1.getId());
		System.out.println("Saved student. Generated ID: " + student2.getId());
		System.out.println("Saved student. Generated ID: " + student3.getId());
	}

	public Student findStudentById(StudentDAO studentDAO, Integer id) {
		System.out.println("Finding student...");
        try {
            return studentDAO.findById(id);
		}catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}

	private void findAllStudents(StudentDAO studentDAO) {
		System.out.println("Finding all students...");
		List<Student> students = studentDAO.findAll();

		for(Student student:students){
			System.out.println(student);
		}
	}

	private void findByName(StudentDAO studentDAO){
		System.out.println("Finding student who has given name...");
		List<Student> students = studentDAO.findByName("Yusuf3");

		for(Student student:students){
			System.out.println(student);
		}
	}

	private void updateStudent(StudentDAO studentDAO, Integer id){
		// find object which will be update
		Student student = studentDAO.findById(id);

		// make change
		student.setFirstName("YusufX");
		studentDAO.update(student);

		// display
		System.out.println(student);
	}

	private void deleteStudent(StudentDAO studentDAO, Integer id){
		System.out.println("Deleting student id: " + id);
		studentDAO.delete(id);
	}

	private void deleteAllStudents(StudentDAO studentDAO){
		System.out.println("Deleting all students..");
		int numberOfRowsDeleted = studentDAO.deleteAll();
		System.out.println(numberOfRowsDeleted + " rows deleted.");
	}
}
