package com.takeHospital.service.metodsScale;

import com.takeHospital.domain.parametrsForScheme.ParamScheme;
import com.takeHospital.repository.SchemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SNAPPEService implements MedScale {

    @Autowired
    private SchemeRepository schemeRepository;

    private int countBalls;

    public SNAPPEService() {
    }

    public SNAPPEService(int countBalls) {
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
        ParamScheme snappe = schemeRepository.findByNameSheme("snappe");

        return (balls * snappe.getColibrProc()) / snappe.getColibrBall();
    }
}
