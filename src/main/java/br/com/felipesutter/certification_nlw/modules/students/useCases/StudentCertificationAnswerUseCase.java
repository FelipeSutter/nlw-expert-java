package br.com.felipesutter.certification_nlw.modules.students.useCases;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipesutter.certification_nlw.modules.questions.entities.QuestionEntity;
import br.com.felipesutter.certification_nlw.modules.questions.repositories.QuestionRepository;
import br.com.felipesutter.certification_nlw.modules.students.dto.StudentCertificationAnswerDTO;
import br.com.felipesutter.certification_nlw.modules.students.dto.VerifyHasVerificationDTO;
import br.com.felipesutter.certification_nlw.modules.students.entities.AnswersCertificationEntity;
import br.com.felipesutter.certification_nlw.modules.students.entities.CertificationStudentEntity;
import br.com.felipesutter.certification_nlw.modules.students.entities.StudentEntity;
import br.com.felipesutter.certification_nlw.modules.students.repository.CertificationStudentRepository;
import br.com.felipesutter.certification_nlw.modules.students.repository.StudentRepository;

@Service
public class StudentCertificationAnswerUseCase {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CertificationStudentRepository certificationRepository;

    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    public CertificationStudentEntity execute(StudentCertificationAnswerDTO dto) throws Exception {

        var hasCertification = this.verifyIfHasCertificationUseCase
                .execute(new VerifyHasVerificationDTO(dto.getEmail(), dto.getTechnology()));

        if (hasCertification) {
            throw new Exception("Você já tirou a sua certificação");
        }

        List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(dto.getTechnology());
        List<AnswersCertificationEntity> answersCertifications = new ArrayList<>();

        // Serve para manipular variáveis integer dentro de expressões lambda
        AtomicInteger correctAnswers = new AtomicInteger(0);

        dto.getQuestionAnswers()
                .stream().forEach(questionAnswer -> {
                    var question = questionsEntity.stream()
                            .filter(q -> q.getId().equals(questionAnswer.getQuestionID())).findFirst().get();

                    var findCorrectAlternative = question.getAlternatives().stream()
                            .filter(alternative -> alternative.isCorrect()).findFirst().get();

                    if (findCorrectAlternative.getId().equals(questionAnswer.getAlternativeID())) {
                        questionAnswer.setCorrect(true);
                        correctAnswers.incrementAndGet();
                    } else {
                        questionAnswer.setCorrect(false);
                    }

                    var answersCertificationEntity = AnswersCertificationEntity.builder()
                            .answerID(questionAnswer.getAlternativeID())
                            .questionID(questionAnswer.getQuestionID())
                            .isCorrect(questionAnswer.isCorrect()).build();

                    answersCertifications.add(answersCertificationEntity);

                });

        // Verificar se existe estudante

        var student = studentRepository.findByEmail(dto.getEmail());
        UUID studentID;
        if (student.isEmpty()) {
            var studentCreated = StudentEntity.builder().email(dto.getEmail()).build();
            studentCreated = studentRepository.save(studentCreated);
            studentID = studentCreated.getId();
        } else {
            studentID = student.get().getId();
        }

        CertificationStudentEntity certificationStudentEntity = CertificationStudentEntity.builder()
                .technology(dto.getTechnology())
                .studentID(studentID)
                .grate(correctAnswers.get())
                .build();

        var certificationStudentCreated = certificationRepository.save(certificationStudentEntity);

        answersCertifications.stream().forEach(answersCertification -> {
            answersCertification.setCertificationID(certificationStudentEntity.getId());
            answersCertification.setCertificationStudentEntity(certificationStudentEntity);
        });

        certificationStudentEntity.setAnswersCertificationEntity(answersCertifications);

        certificationRepository.save(certificationStudentEntity);

        return certificationStudentCreated;

    }

}
