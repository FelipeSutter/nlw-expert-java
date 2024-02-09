package br.com.felipesutter.certification_nlw.modules.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipesutter.certification_nlw.modules.students.dto.VerifyHasVerificationDTO;
import br.com.felipesutter.certification_nlw.modules.students.useCases.VerifyIfHasCertificationUseCase;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    VerifyIfHasCertificationUseCase useCase;

    @PostMapping("/verifyIfHasCertification")
    public String verifyIfHasCertification(@RequestBody VerifyHasVerificationDTO verifyHasVerificationDTO) {
        var result = this.useCase.execute(verifyHasVerificationDTO);
        if (result) {
            return "Usuário já fez a prova";
        }

        return "Usuário pode fazer a prova";
    }

}
