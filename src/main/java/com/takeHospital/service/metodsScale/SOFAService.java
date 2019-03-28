package com.takeHospital.service.metodsScale;

import com.takeHospital.domain.parametrsForScheme.ParamScheme;
import com.takeHospital.repository.SchemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SOFAService implements MedScale {

    @Autowired
    private SchemeRepository schemeRepository;

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
        ParamScheme sofa = schemeRepository.findByNameSheme("sofa");
        return (balls * sofa.getColibrProc()) / sofa.getColibrBall();
    }
}
