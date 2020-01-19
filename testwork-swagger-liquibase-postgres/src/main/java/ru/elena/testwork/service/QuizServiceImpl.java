package ru.elena.testwork.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.elena.testwork.base.QuizService;
import ru.elena.testwork.dao.QuestionRepository;

import ru.elena.testwork.dao.QuizRepository;
import ru.elena.testwork.domain.Question;
import ru.elena.testwork.domain.Quiz;

@Service("quizService")
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Quiz findByName(String name) {
        return name == null ? null : quizRepository.findByName(name);
    }

    @Override
    public List<Quiz> findByStartDate(String startDate) {
        return startDate == null ? null : quizRepository.findByStartDate(startDate);
    }

    @Override
    public List<Quiz> findByEndDate(String endDate) {
        return endDate == null ? null : quizRepository.findByEndDate(endDate);
    }

    @Override
    public List<Quiz> findPage(int page, int size) {
        return quizRepository.fingPage(page, size);
    }

    @Override
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    @Override
    @Transactional
    public Long save(Long id, String name, String start, String end, boolean isActive, List<Question> questions) {
        Quiz quiz = new Quiz();
        quiz.setId(id);
        quiz.setName(name);
        quiz.setStartDate(start);
        quiz.setEndDate(end);
        quiz.setIsActive(isActive);
        quizRepository.save(quiz);
        for (Question question : questions) {
            
            question.setQuiz(quiz);
            questionRepository.save(question);
        }
        return quiz.getId();
    }

    @Override
    public void deleteById(Long id) {
        if (id != null && id > 0) {
            quizRepository.delete(id);
        }

    }

    @Override
    public Quiz findById(Long id) {
        return id == null ? null : quizRepository.findById(id);
    }
}
