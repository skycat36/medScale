package com.takeHospital.service.metodsScale;

import com.takeHospital.domain.parametrsForScheme.ParamScheme;
import com.takeHospital.repository.SchemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NTISSService implements MedScale {

    @Autowired
    private SchemeRepository schemeRepository;

    private int countBalls;

    public NTISSService() {
    }

    public NTISSService(int countBalls) {
        this.countBalls = countBalls;
    }

    public void setCountBalls(int countBalls) {
        this.countBalls = countBalls;
    }

    @Override
    public int getCountBalls() {
        return this.countBalls;
    }

    @Override
    public int getCountProcLethalOutcome(int balls) {
        ParamScheme ntiss = schemeRepository.findByNameSheme("ntiss");

        return (balls * ntiss.getColibrProc()) / ntiss.getColibrBall();
    }
}
