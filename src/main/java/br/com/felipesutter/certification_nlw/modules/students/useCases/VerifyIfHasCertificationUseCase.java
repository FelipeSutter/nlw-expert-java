package br.com.felipesutter.certification_nlw.modules.students.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipesutter.certification_nlw.modules.students.dto.VerifyHasVerificationDTO;
import br.com.felipesutter.certification_nlw.modules.students.repository.CertificationStudentRepository;

@Service
public class VerifyIfHasCertificationUseCase {

    @Autowired
    CertificationStudentRepository repository;

    public boolean execute(VerifyHasVerificationDTO dto) {
        var result = this.repository.findByStudentEmailAndTechnology(dto.getEmail(), dto.getTechnology());
        if (!result.isEmpty()) {
            return true;
        }
        return false;
    }

}
