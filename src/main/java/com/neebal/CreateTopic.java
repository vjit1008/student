package com.neebal;

import com.neebal.entities.Question;
import com.neebal.entities.QuestionOptions;
import com.neebal.entities.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class CreateTopic {
    public static void main(String[] args) {

        QuestionOptions opt1 = new QuestionOptions("A. Venus", false);
        QuestionOptions opt2 = new QuestionOptions("B. Mars", true);
        QuestionOptions opt3 = new QuestionOptions("C. Jupiter", false);
        QuestionOptions opt4 = new QuestionOptions("D. Saturn", false);

        QuestionOptions opt5 = new QuestionOptions("A. Sahara Desert", false);
        QuestionOptions opt6 = new QuestionOptions("B. Antarctic Desert", true);
        QuestionOptions opt7 = new QuestionOptions("C. Mojave Desert", false);
        QuestionOptions opt8 = new QuestionOptions("D. Gobi Desert", false);

        Question question1 = new Question("Which planet is known as the 'Red Planet'?", 5);
        Question question2 = new Question("Which desert is the largest cold desert in the world?", 10);

        Topic topic = new Topic("Space and Geography");

        opt1.setQuestion(question1);
        opt2.setQuestion(question1);
        opt3.setQuestion(question1);
        opt4.setQuestion(question1);

        opt5.setQuestion(question2);
        opt6.setQuestion(question2);
        opt7.setQuestion(question2);
        opt8.setQuestion(question2);

        Set<QuestionOptions> questionOptionSet = new HashSet<>();
        questionOptionSet.add(opt1);
        questionOptionSet.add(opt2);
        questionOptionSet.add(opt3);
        questionOptionSet.add(opt4);
        question1.setQuestion_optionSet(questionOptionSet);

        Set<QuestionOptions> questionOptionSet1 = new HashSet<>();
        questionOptionSet1.add(opt5);
        questionOptionSet1.add(opt6);
        questionOptionSet1.add(opt7);
        questionOptionSet1.add(opt8);
        question2.setQuestion_optionSet(questionOptionSet1);

        question1.setTopic(topic);
        question2.setTopic(topic);
        Set<Question> questionSet = new HashSet<>();
        questionSet.add(question1);
        questionSet.add(question2);
        topic.setQuestionSet(questionSet);

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(topic);
            transaction.commit();
        }
    }
}