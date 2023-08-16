package com.neebal;

import com.neebal.entities.Exam;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CreateExam {

    public static void main(String[] args) {
        Exam exam = new Exam("Exam1",60.0,50l);

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            session.save(exam);
            transaction.commit();
        }
    }
}
