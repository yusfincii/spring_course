package com.yusuf.cruddemo;

import com.yusuf.cruddemo.dao.AppDAO;
import com.yusuf.cruddemo.entity.Instructor;
import com.yusuf.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
			deleteInstructorDetail(appDAO);

		};
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
