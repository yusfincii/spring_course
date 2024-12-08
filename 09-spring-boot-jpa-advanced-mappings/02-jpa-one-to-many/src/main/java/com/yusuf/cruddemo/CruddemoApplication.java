package com.yusuf.cruddemo;

import com.yusuf.cruddemo.dao.AppDAO;
import com.yusuf.cruddemo.entity.Course;
import com.yusuf.cruddemo.entity.Instructor;
import com.yusuf.cruddemo.entity.InstructorDetail;
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
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner -> {
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			//deleteInstructorDetail(appDAO);
			//createInstructorWithCourses(appDAO);
			//findInstructorWithCourses(appDAO);
			//findCourseInstructor(appDAO);
			//findCoursesByInstructorId(appDAO);
			findInstructorWithCoursesJoinFetch(appDAO);
		};
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
