package com.takeHospital.service.metodsScale;

import com.takeHospital.domain.parametrsForScheme.SOFA;
import com.takeHospital.repository.repositoryScheme.SOFARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SOFAService implements MedScale {

    @Autowired
    private SOFARepository sofaRepository;

    private int countBalls;

    public SOFAService() {
    }

    public SOFAService(int countBalls) {
        this.countBalls = countBalls;
    }

    @Override
    public int getCountBalls() {
        return this.countBalls;
    }

    @Override
    public int getCountProcLethalOutcome(int balls) {
        SOFA sofa = sofaRepository.findAll().get(0);
        return (balls * sofa.getColibrProc()) / sofa.getColibrBall();
    }
}
