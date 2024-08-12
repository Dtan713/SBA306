package sba.sms.services;

import jakarta.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sba.sms.dao.CourseI;
import sba.sms.models.Course;
import sba.sms.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * CourseService is a concrete class. This class implements the
 * CourseI interface, overrides all abstract service methods and
 * provides implementation for each method.
 */
public class CourseService implements CourseI {

    SessionFactory factory = new Configuration().configure().buildSessionFactory();
    Session session = null;

    @Override
    public void createCourse(Course course) {
        transaction tranaction = null;

        try {
            session = factory.openSession();
            tranaction = session.beginTransaction();

            session.persist(course);

            tranaction.commit();
        } catch (Exception e) {
            if (tranaction != null ){
                tranaction.rollback();
            }
            System.out.println(e);
        }
    }
 public Course getCourseById (int courseID){

        Course course = null;
        Transaction transaction = null;

 try {
     session = factory.openSession();
     transaction = session.beginTransaction();

     course = session.get(entityType:Course.class, courseID )

     transaction.commit();

 }catch (Exception e) {
     if (transaction != null) {
         transaction.rollback();
     }
     System.out.println(e);
 }

 return course;

     }

    @Override
    public List<Course> getAllCourses() {
        try {
            session = factory.openSession();
            tranaction = session.beginTransaction();

            String hql = "SELECT course FROM Course course";

            TypedQuery<Course> query = session.createQuery(hql, resultClass:Course.class)
            return query.getResultList();

        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();



        }
    }
}
