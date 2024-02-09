package br.com.felipesutter.certification_nlw.modules.students.useCases;

import org.springframework.stereotype.Service;

import br.com.felipesutter.certification_nlw.modules.students.dto.VerifyHasVerificationDTO;

@Service
public class VerifyIfHasCertificationUseCase {

    public boolean execute(VerifyHasVerificationDTO dto) {
        if (dto.getEmail().equals("felipesutter@gmail.com") && dto.getTechnology().equals("Java")) {
            return true;
        }
        return false;
    }

}
