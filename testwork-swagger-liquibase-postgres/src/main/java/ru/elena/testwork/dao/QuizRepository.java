package ru.elena.testwork.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import ru.elena.testwork.domain.Quiz;

@Repository
public class QuizRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Quiz quiz) {
        if (quiz.getId() == null) {
            em.persist(quiz);
        } else {
            em.merge(quiz);
        }
        return quiz.getId();
    }

    public Quiz findById(Long id) {
        return em.find(Quiz.class, id);
    }

    public void delete(Long id) {
        Quiz quiz = em.find(Quiz.class, id);
        em.remove(quiz);
    }

    public List<Quiz> findAll() {
        TypedQuery<Quiz> query = em.createQuery("select q from Quiz q", Quiz.class);
        return query.getResultList();
    }

    public List<Quiz> fingPage(int pageNumber, int pageSize) {
        TypedQuery<Quiz> query = em.createQuery("select q from Quiz q", Quiz.class);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public Quiz findByName(String name) {
        TypedQuery<Quiz> query = em.createQuery("select q from Quiz q where q.name=:name", Quiz.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    public List<Quiz> findByStartDate(String start) {
        TypedQuery<Quiz> query = em.createQuery("select q from Quiz q where q.startDate=:start", Quiz.class);
        query.setParameter("start", start);
        return query.getResultList();
    }

    public List<Quiz> findByEndDate(String end) {
        TypedQuery<Quiz> query = em.createQuery("select q from Quiz q where q.endDate=:end", Quiz.class);
        query.setParameter("end", end);
        return query.getResultList();
    }

}

