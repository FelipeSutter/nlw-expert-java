package br.com.felipesutter.certification_nlw.modules.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipesutter.certification_nlw.modules.students.dto.StudentCertificationAnswerDTO;
import br.com.felipesutter.certification_nlw.modules.students.dto.VerifyHasVerificationDTO;
import br.com.felipesutter.certification_nlw.modules.students.useCases.StudentCertificationAnswerUseCase;
import br.com.felipesutter.certification_nlw.modules.students.useCases.VerifyIfHasCertificationUseCase;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private VerifyIfHasCertificationUseCase verifyCertificationUseCase;

    @Autowired
    private StudentCertificationAnswerUseCase studentCertificationUseCase;

    @PostMapping("/verifyIfHasCertification")
    public String verifyIfHasCertification(@RequestBody VerifyHasVerificationDTO verifyHasVerificationDTO) {
        var result = this.verifyCertificationUseCase.execute(verifyHasVerificationDTO);
        if (result) {
            return "Usuário já fez a prova";
        }

        return "Usuário pode fazer a prova";
    }

    @PostMapping("/certication/answer")
    public ResponseEntity<Object> certificationAnswer(@RequestBody StudentCertificationAnswerDTO studentDTO) {
        try {
            var result = this.studentCertificationUseCase.execute(studentDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
