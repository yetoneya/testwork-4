package ru.elena.testwork.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.elena.testwork.base.QuizService;
import ru.elena.testwork.domain.Question;
import ru.elena.testwork.domain.Quiz;
import ru.elena.testwork.newpackage.dto.QuestionDto;
import static ru.elena.testwork.newpackage.dto.QuestionDto.toQuestionDto;
import ru.elena.testwork.newpackage.dto.QuizDto;
import static ru.elena.testwork.newpackage.dto.QuizDto.toQuizDto;

@RestController
public class QuizRestController {

    private static final List<Question> QUESTIONS = new ArrayList<>();

    @Autowired
    private QuizService quizService;

    @RequestMapping("/create/question")
    public ResponseEntity<Integer> createQuestion(@RequestParam int seq, @RequestParam String content) {
        Question question = new Question(null, seq, content, null);
        if (QUESTIONS.stream()
                .anyMatch(q -> q.getSeq() == seq)) {
            return new ResponseEntity<>(seq, HttpStatus.CONFLICT);
        }
        QUESTIONS.add(question);
        return new ResponseEntity<>(seq, HttpStatus.OK);
    }

    @RequestMapping("/create/quiz")
    public ResponseEntity<Long> createQuiz(@RequestParam String name,
            @RequestParam String start,
            @RequestParam String end,
            @RequestParam boolean active) {
        if (start.compareTo(end) > 0) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        Long id = quizService.save(null, name, start, end, active, new ArrayList<>(QUESTIONS));
        clearQuestions();
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    static void clearQuestions() {
        QUESTIONS.clear();
    }

    @RequestMapping("/delete")
    public ResponseEntity<Integer> deleteQuiz(@RequestParam int id) {
        quizService.deleteById((long) id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @RequestMapping("/show")
    public ResponseEntity<QuizDto> showQuiz(@RequestParam int id) {
        Quiz quiz = quizService.findById((long) id);
        if (quiz == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        QUESTIONS.clear();
        QUESTIONS.addAll(quiz.getQuestions());
        
        return new ResponseEntity<>(toQuizDto(quiz), HttpStatus.OK);
    }

    @RequestMapping("/get/question")
    public ResponseEntity<QuestionDto> getQuestion(@RequestParam int id) {
        Question question = QUESTIONS.stream()
                .filter(Objects::nonNull)             
                .filter(q -> q.getId() == id)
                .findFirst()
                .orElse(null);
        System.out.println("Q=" + question.getContent());
        return question == null
                ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(toQuestionDto(question), HttpStatus.OK);
    }

    @RequestMapping("/update/question")
    public ResponseEntity<Integer> saveQuestion(@RequestParam int id, @RequestParam String content) {
        QUESTIONS.forEach(question -> {
            if (question.getId() == id) {
                question.setContent(content);
            }
        });
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @RequestMapping("/update/quiz")
    public ResponseEntity<Long> updateQuiz(@RequestParam long id,
            @RequestParam String name,
            @RequestParam String start,
            @RequestParam String end,
            @RequestParam boolean active) {
        if (start.compareTo(end) > 0) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        id = quizService.save(id, name, start, end, active, new ArrayList<>(QUESTIONS));
        clearQuestions();
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex.getMessage());
        mav.setViewName("error");
        return mav;
    }
}
