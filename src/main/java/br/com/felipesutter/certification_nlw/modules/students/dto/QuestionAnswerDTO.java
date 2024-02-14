package br.com.felipesutter.certification_nlw.modules.students.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionAnswerDTO {

    private UUID questionID;
    private UUID alternativeID;
    private boolean isCorrect;

}
