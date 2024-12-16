package com.yusuf.cruddemo;

import com.yusuf.cruddemo.dao.AppDAO;
import com.yusuf.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner -> {
			// createCourseAndStudents(appDAO);
			// retrieveCourseAndStudents(appDAO);
			// retrieveStudentAndCourses(appDAO);
			// addMoreCoursesForAStudent(appDAO);
			// deleteCourse(appDAO);
			// deleteStudentById(appDAO);
		};
	}

	private void deleteStudentById(AppDAO appDAO) {
		int theId = 3;
		appDAO.deleteStudentById(theId);
	}

	private void addMoreCoursesForAStudent(AppDAO appDAO) {
		int theId = 3;
		// related student
		Student student = appDAO.findStudentAndCourseById(theId);

		// course instances
		Course course1 = new Course("Salsa");
		//Course course2 = new Course("English Speaking");

		student.addCourse(course1);
		//student.addCourse(course2);

		System.out.println("Updating student : " + student.getFirst_name() + " " + student.getLast_name());
		System.out.println("Associated courses : " + student.getCourses()); // temporary case
		appDAO.updateStudent(student);
		System.out.println("DONE!");
	}

	private void retrieveStudentAndCourses(AppDAO appDAO) {
		int theId = 1;
		Student student = appDAO.findStudentAndCourseById(theId);

		System.out.println(" --- " + student.getFirst_name() + " - " + student.getLast_name() + " --- ");
		for (Course courseX : student.getCourses()){
			System.out.println(courseX.getTitle());
		}
		// do not need that alter the fetch type as EAGER of
		// course list which belongs student class
	}

	private void retrieveCourseAndStudents(AppDAO appDAO) {
		int theId = 10;
		Course course = appDAO.findCourseAndStudentsById(theId);

		System.out.println(" --- " + course.getTitle() + " --- ");
		for (Student student : course.getStudents()){
			System.out.println(student.getFirst_name() + " - " + student.getLast_name());
		}
		// do not need that alter the fetch type as EAGER of
		// student list which belongs course class
	}

	private void createCourseAndStudents(AppDAO appDAO) {
		Course course = new Course("Guitar");

		Student student1 = new Student("Yusuf", "INCI", "yusuf@gmail.com");
		Student student2 = new Student("Ali", "AKAR", "ali@hotmail.com");

		course.addStudent(student1);
		course.addStudent(student2);

		System.out.println("Course: " + course.getTitle());
		System.out.println("Students: " + course.getStudents());
		appDAO.save(course);
		System.out.println(" --- DONE! ---");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId = 6;
		System.out.println("Course which will be delete: " + appDAO.findCourse(theId).getTitle());
		appDAO.deleteCourse(theId);
		// reviews will be deleted automatically
		// because of CascadeType.ALL
		System.out.println("DONE !");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		int theId = 4;
		Course course = appDAO.findCourseAndReviewsById(theId);
		System.out.println(" --- " + course.getTitle() + " --- ");
		for (Review review : course.getReviews()){
			System.out.println(review.getId() + " - " + review.getComment());
		}
		// do not need that alter the review list of a
		// course object's fetch type as EAGER
	}

	private void createCourseWithReviews(AppDAO appDAO) {
		// reviews
		Review goodReview = new Review("It was so instructive, Great !!");
		Review badReview = new Review("I don't like it :( ");
		List<Review> reviews = new ArrayList<>();
		reviews.add(goodReview);
		reviews.add(badReview);

		// course
		Course course = new Course("PL/SQL Course");
		course.setReviews(reviews);
		course.addReview(new Review("Not bad :| "));

		appDAO.save(course);

		System.out.println("Course: " + course.getTitle() + "\nReviews: " +
				course.getReviews());
	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 11;
		Course course = appDAO.findCourse(theId);
		System.out.println("The course will be delete: " + course);

		appDAO.deleteCourse(theId);
	}

	private void deleteInstructorWithRemoveAssociatedCourses(AppDAO appDAO){
		int theId = 1;

		Instructor instructor = appDAO.findInstructorById(theId);
		System.out.println("Instructor who will be delete : " + instructor);

		appDAO.deleteInstructorWithAssociatedCourse(theId);
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 1;
		Course course = appDAO.findCourse(theId);
		System.out.println("Course which will be update: " + course);

		course.setTitle("Electro Guitar");
		appDAO.updateCourse(course);
		System.out.println("Updated course info: " + course);
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId = 1;
		Instructor instructor = appDAO.findInstructorById(theId);
		System.out.println("Instructor who will be update: " + instructor);

		instructor.setLast_name("INCIx");
		appDAO.updateInstructor(instructor);
		System.out.println("Updated instructor: " + instructor);
	}

	// do not need EAGER fetch type via sql
	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId = 1;
		Instructor instructor = appDAO.findInstructorByIdJoinFetch(theId);
		System.out.println(instructor.toString());

		// associate objects - instructor & courses
		System.out.println(instructor.getCourses());
	}

	// method with LAZY -as default fetch type
	private void findCoursesByInstructorId(AppDAO appDAO) {
		int theId = 1;
		Instructor instructor = appDAO.findInstructorById(theId);
		System.out.println("Instructor: " + instructor.getFirst_name());

		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		// associate the objects
		instructor.setCourses(courses);
		System.out.println("Courses: " + instructor.getCourses());

	}

	private void findCourseInstructor(AppDAO appDAO) {
		int theId = 1;
		Course course = appDAO.findCourse(theId);
		System.out.println("Course : " + course.getTitle());
		System.out.println("Course instructor : " + course.getInstructor().getFirst_name());


	}

	// method with EAGER fetch type
	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 1;
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("Instructor: " + tempInstructor);

		System.out.println("Courses: " + tempInstructor.getCourses());
		System.out.println("Done!");

	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor instructor = new Instructor("Yusuf", "INCI",
				"ysfnc27@gmail.com");
		InstructorDetail instructorDetail= new InstructorDetail("yoyusux",
				"ski");
		// associate the objects
		instructor.setInstructorDetail(instructorDetail);

		Course course1 = new Course("Reed");
		Course course2 = new Course("Salsa");
		instructor.add(course1);
		instructor.add(course2);

		// this will also save the course
		// because of CascadeType.PERSIST
		appDAO.save(instructor);

		System.out.println("Done!");

	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 3;
		System.out.println("deleting instructor detail: " + theId);
		appDAO.deleteInstructorDetailById(theId);
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int theId = 2;

		//  get instructor detail object
		InstructorDetail instructorDetail  = appDAO.findInstructorDetailById(theId);

		System.out.println("Instructor Detail : " + instructorDetail);

		// associated instructor
		System.out.println("Associated instructor: " + instructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO){
		int theId = 1;
		System.out.println("deleting instructor: " + theId);
		appDAO.deleteInstructorById(theId);

		System.out.println("Done!");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated instructorDetail only: " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {

		Instructor instructor1 = new Instructor("Yusuf", "INCI",
				"ysfnc27@gmail.com");
		InstructorDetail instructorDetail1 = new InstructorDetail("yoyusux",
				"ski");
		// associate the objects
		instructor1.setInstructorDetail(instructorDetail1);

		Instructor instructor2 = new Instructor("Yusuf2", "INCI",
				"ysfnc27@gmail.com");
		InstructorDetail instructorDetail2 = new InstructorDetail("yoyusux2",
				"football");
		// associate the objects
		instructor2.setInstructorDetail(instructorDetail2);

		// this will also save the details object
		// because of CascadeType.ALL
		System.out.println("Saving instructor: " + instructor1);
		appDAO.save(instructor2);
		System.out.println("Done.");
	}
}
