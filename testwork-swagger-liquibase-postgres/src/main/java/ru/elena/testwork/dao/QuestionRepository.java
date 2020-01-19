package ru.elena.testwork.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.elena.testwork.domain.Question;

@Repository
public class QuestionRepository {

    @PersistenceContext
    private EntityManager em;

    public Question save(Question question) {
        if (question.getId() == null) {
            em.persist(question);
        } else {
            em.merge(question);
        }
        return question;
    }

    public void delete(Long id) {
        Question question = em.find(Question.class, id);
        em.remove(question);
    }
}

