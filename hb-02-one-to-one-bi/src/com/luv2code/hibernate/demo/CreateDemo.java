package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			
			//create the objects
			Instructor instructor = new Instructor("Sam","Parloure","sam@gemail.com");
			InstructorDetail instDetail = new InstructorDetail("http://www.youtube.com/sam.parloure","musician");
			//associate the objects
			instructor.setInstructorDetail(instDetail);
			
			//start transaction
			session.beginTransaction();
			
			//save the instructor
			//this will also save the instructorDetail object
			//because of the CascadeType.ALL
			System.out.println("Saving instructor: " + instructor);
			System.out.println("Saving instDetail: " + instDetail);
			session.save(instructor);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			factory.close();
		}
		
	}

}
