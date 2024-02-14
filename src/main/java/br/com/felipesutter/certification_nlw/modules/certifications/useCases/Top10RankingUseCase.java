package br.com.felipesutter.certification_nlw.modules.certifications.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipesutter.certification_nlw.modules.students.entities.CertificationStudentEntity;
import br.com.felipesutter.certification_nlw.modules.students.repository.CertificationStudentRepository;

@Service
public class Top10RankingUseCase {

    @Autowired
    private CertificationStudentRepository certificationRepository;

    public List<CertificationStudentEntity> execute() {
        return this.certificationRepository.findTop10ByOrderByGradeDesc();
    }

}
