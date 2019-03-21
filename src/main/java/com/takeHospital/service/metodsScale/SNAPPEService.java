package com.takeHospital.service.metodsScale;

import com.takeHospital.domain.parametrsForScheme.SNAPPE;
import com.takeHospital.repository.repositoryScheme.SNAPPERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SNAPPEService implements MedScale {

    @Autowired
    private SNAPPERepository snappeRepository;

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
        SNAPPE snappe = snappeRepository.findAll().get(0);

        return (balls * snappe.getColibrProc()) / snappe.getColibrBall();
    }
}
