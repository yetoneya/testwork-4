package ru.elena.testwork.newpackage.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.elena.testwork.domain.Question;
import ru.elena.testwork.domain.Quiz;
import static ru.elena.testwork.newpackage.dto.QuestionDto.toQuestionDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {

    private Long id;

    private String name;

    private String start;

    private String end;

    private Boolean active;

    private List<QuestionDto> questions;

    public static QuizDto toQuizDto(Quiz quiz) {
      List<QuestionDto>questions = new ArrayList<>();
      for(Question q: quiz.getQuestions()){
          questions.add(toQuestionDto(q));
      }
      return new QuizDto(quiz.getId(),quiz.getName(),quiz.getStartDate(),quiz.getEndDate(),quiz.getIsActive(),questions);
    }
}
