package ru.elena.testwork.controller;

import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.elena.testwork.base.QuizService;
import ru.elena.testwork.domain.Quiz;
import static ru.elena.testwork.controller.QuizRestController.clearQuestions;
import ru.elena.testwork.service.QuizServiceImpl;

@Controller
public class OuizController {

    private final QuizService quizService;
    
    @Autowired
    public OuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/")
    public String home(Model model) {
        return "action";
    }

    @GetMapping("/action")
    public String action(@RequestParam String action, Model model) {
        switch (action) {
            case "create":
                clearQuestions();
                return "create";
            case "find":
                return "find";
            default:
                return "error";
        }
    }

    @RequestMapping("/byname")
    public String byname(@RequestParam String name, Model model) {
        Quiz quiz = quizService.findByName(name);
        List<Quiz> quizes = Lists.newArrayList();
        if (quiz != null) {
            quizes.add(quiz);
        }
        model.addAttribute("quizes", quizes);
        return "list";
    }

    @RequestMapping("/bystart")
    public String byStartDate(@RequestParam String start, Model model) {
        System.out.println(start);
        List<Quiz> quizes = quizService.findByStartDate(start);
        model.addAttribute("quizes", quizes);
        return "list";
    }

    @RequestMapping("/byend")
    public String byEndDate(@RequestParam String end, Model model) {
        List<Quiz> quizes = quizService.findByEndDate(end);
        model.addAttribute("quizes", quizes);
        return "list";
    }

    @RequestMapping("/page")
    public String page(@RequestParam int page, @RequestParam int size, Model model) {
        List<Quiz> quizes = quizService.findPage(page, size);
        model.addAttribute("quizes", quizes);
        return "list";
    }

    @RequestMapping("/all")
    public String all(Model model) {
        List<Quiz> quizes = quizService.findAll();
        model.addAttribute("quizes", quizes);
        return "list";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex.getMessage());
        mav.setViewName("error");
        return mav;
    }
}
