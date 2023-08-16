package com.neebal;



import com.neebal.entities.Exam;
import com.neebal.entities.ExamQuestion;
import com.neebal.entities.Question;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.Scanner;
import java.util.Set;

public class CreateExamQuestion {
    public static void main(String[] args) {

        long examId;
        long questionId;

        try(Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter exam id: ");
            examId = scanner.nextLong();

            System.out.println("enter question id: ");
            questionId = scanner.nextLong();
        }

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()){
            Exam exam =session.get(Exam.class, examId);
            Question question = session.get(Question.class,questionId);

            ExamQuestion eq = new ExamQuestion(
                    exam,
                    question);
            Transaction transaction = session.beginTransaction();
            session.save(eq);
            transaction.commit();
        }
    }
}