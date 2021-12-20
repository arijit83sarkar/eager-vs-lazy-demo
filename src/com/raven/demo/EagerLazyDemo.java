package com.raven.demo;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.raven.entity.Course;
import com.raven.entity.Instructor;
import com.raven.entity.InstructorDetails;

public class EagerLazyDemo {
    public static void main(String[] args) {
		System.out.println(">>>>>>> Welcome Eager-vs-Lazy Demo <<<<<<<<");
		SessionFactory sessionFactory = null;
		Session session = null;

		try {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
					.addAnnotatedClass(InstructorDetails.class).addAnnotatedClass(Course.class).buildSessionFactory();
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();

			// get the instructor
			int id = 1;
			Instructor instructor = session.get(Instructor.class, id);

			System.out.println(">>> Instructor :: " + instructor);

			// get courses for the instructor
			List<Course> courses = instructor.getCourses();
			System.out.println(">>> Instructor Courses :: " + courses);

			session.getTransaction().commit();
			System.out.println(">>>> Done <<<");
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
}
