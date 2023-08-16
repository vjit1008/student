package com.neebal;


import com.neebal.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CreateStudent {

    public static void main(String[] args) {
        Student student = new Student("vish",'M',25);

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        }
    }
}