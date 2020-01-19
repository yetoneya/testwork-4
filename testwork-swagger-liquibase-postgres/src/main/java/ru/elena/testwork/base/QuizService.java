package ru.elena.testwork.base;

import java.util.List;
import ru.elena.testwork.domain.Question;
import ru.elena.testwork.domain.Quiz;

public interface QuizService {
    
   Quiz findByName(String name);
   
   List<Quiz>findByStartDate(String startDate);
   
   List<Quiz>findByEndDate(String endDate);
   
   List<Quiz>findPage(int page, int size);
   
   List<Quiz>findAll();
   
   Long save(Long id, String name, String start, String end, boolean isActive, List<Question>questions);

   void deleteById(Long id);
   
   Quiz findById(Long id);
    
}
