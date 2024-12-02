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
			createInstructor(appDAO);
		};
	}

	private void createInstructor(AppDAO appDAO) {
		/*
		Instructor instructor = new Instructor("Yusuf", "INCI",
				"ysfnc27@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("yoyusux",
				"ski");
		// associate the objects
		instructor.setInstructor_detail_id(instructorDetail);
		 */
		Instructor instructor = new Instructor("Yusuf2", "INCI",
				"ysfnc27@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("yoyusux2",
				"football");
		// associate the objects
		instructor.setInstructor_detail_id(instructorDetail);

		// this will also save the details object
		// because of CascadeType.ALL
		System.out.println("Saving instructor: " + instructor);
		appDAO.save(instructor);
		System.out.println("Done.");
	}
}
