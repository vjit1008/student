package com.neebal;

import com.neebal.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class StudentQuery {

        private static void findAllStudent(Session session) {

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Student> cq = cb.createQuery(Student.class);

            Root<Student> root = cq.from(Student.class);
            cq.select(root);

            List<Student> students = session.createQuery(cq).getResultList();
            System.out.println(students);
        }

        private static void findAllStudentNamesAndScoreByScore(Session session){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

            Root<Student> root = cq.from(Student.class);
            Root<StudentsExams> studentRoot= cq.from(StudentsExams.class);
            cq.multiselect(root.get("name"),studentRoot.get("score")).where(cb.greaterThan(studentRoot.get("score"),22))
                    .orderBy(cb.desc(studentRoot.get("score")));

            List<Object[]> objects = session.createQuery(cq).getResultList();
            objects.forEach(object -> System.out.println(object[0]+" "+object[1]));

        }

        private static void printStudentAndExam(Session session){

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> query = criteriaBuilder.createQuery(Object[].class);

            Root<StudentsExams> examStudentRoot = query.from(StudentsExams.class);
            Join<StudentsExams, Student> studentJoin = examStudentRoot.join("student");
            Join<StudentsExams, Exam> examJoin = examStudentRoot.join("exam");

            query.multiselect(
                    studentJoin.get("name"),
                    examJoin.get("name")
            );

            TypedQuery<Object[]> typedQuery = session.createQuery(query);
            List<Object[]> results = typedQuery.getResultList();

            for (Object[] result : results) {
                String studentName = (String) result[0];
                String examName = (String) result[1];
                System.out.println("Student: " + studentName + ", Exam: " + examName);
            }
        }


        private static void printQuestionAppearInExam(Session session){

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> query = criteriaBuilder.createQuery(Object[].class);

            Root<ExamQuestion> studentExamRoot = query.from(ExamQuestion.class);
            Join<ExamQuestion, Question> questionJoin = studentExamRoot.join("question");
            Join<ExamQuestion, Exam> examJoin = studentExamRoot.join("exam");

            query.multiselect(
                    questionJoin.get("questionDesc"),
                    examJoin.get("name")
            );

            TypedQuery<Object[]> typedQuery = session.createQuery(query);
            List<Object[]> results = typedQuery.getResultList();

            for (Object[] result : results) {
                String question = (String) result[0];
                String examName = (String) result[1];
                System.out.println("Question: " + question + ", Exam: " + examName);
            }
        }

        public static void StudentGroupByGender(Session session){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

            Root<Student> root = cq.from(Student.class);

            cq.multiselect(root.get("gender"),cb.count(root.get("gender"))).groupBy(root.get("gender"));

            List<Object[]> rows = session.createQuery(cq).getResultList();
            rows.forEach(row -> System.out.println(row[0]+" "+row[1]));
        }

        private static void searchQuestionAccordingTopicId(Session session){

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Question> cq = criteriaBuilder.createQuery(Question.class);

            Root<Question> questionRoot = cq.from(Question.class);
            Join<Question, Topic> topicJoin = questionRoot.join("topic");


            Long Id = 1L;
            cq.where(criteriaBuilder.equal(topicJoin.get("id"), Id));

            TypedQuery<Question> typedQuery = session.createQuery(cq);
            List<Question> questions = typedQuery.getResultList();

            for (Question question : questions) {
                System.out.println("Question ID: " + question.getId());

            }
        }




        public static void main(String[] args) {

            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            try (Session session = sessionFactory.openSession()) {

                 findAllStudent(session);

                 printQuestionAppearInExam(session);
                 findAllStudentNamesAndScoreByScore(session);

                StudentGroupByGender(session);
                 printStudentAndExam(session);
                searchQuestionAccordingTopicId(session);
            }
        }
    }

