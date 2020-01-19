package ru.elena.testwork.newpackage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.elena.testwork.domain.Question;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {

    private Long id;  
    private Integer seq;
    private String content;

    public static QuestionDto toQuestionDto(Question question) {
        return new QuestionDto(question.getId(), question.getSeq(), question.getContent());
    }
}
