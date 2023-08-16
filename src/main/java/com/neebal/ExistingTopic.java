package com.neebal;

import com.neebal.entities.Question;
import com.neebal.entities.QuestionOptions;
import com.neebal.entities.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class ExistingTopic {
    public static void main(String[] args) {

        QuestionOptions opt1 = new QuestionOptions("A. Mumbai", false);
        QuestionOptions opt2 = new QuestionOptions("B. Delhi", false);
        QuestionOptions opt3 = new QuestionOptions("C. Kolkata", false);
        QuestionOptions opt4 = new QuestionOptions("D. Pune", true);

        Question question = new Question("Which city is known as the 'Oxford of the East'?", 5);

        opt1.setQuestion(question);
        opt2.setQuestion(question);
        opt3.setQuestion(question);
        opt4.setQuestion(question);

        Set<QuestionOptions> questionOptionSet = new HashSet<>();
        questionOptionSet.add(opt1);
        questionOptionSet.add(opt2);
        questionOptionSet.add(opt3);
        questionOptionSet.add(opt4);
        question.setQuestion_optionSet(questionOptionSet);

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Topic topic = session.get(Topic.class, 3L);

            Question existingQuestion = findQuestionByDescription(session, question.getQuestionDesc());

            if (existingQuestion == null) {
                question.setTopic(topic);
                session.save(question);
            } else {
                existingQuestion.getQuestion_optionSet().addAll(question.getQuestion_optionSet());
                session.update(existingQuestion);
            }

            transaction.commit();
        }
    }

    private static Question findQuestionByDescription(Session session, String description) {
        return session.createQuery("FROM Question WHERE questionDesc = :description", Question.class)
                .setParameter("description", description)
                .uniqueResult();
    }
}