package com.takeHospital.service.metodsScale;

import com.takeHospital.domain.parametrsForScheme.NTISS;
import com.takeHospital.repository.repositoryScheme.NTISSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NTISSService implements MedScale {

    @Autowired
    private NTISSRepository ntissRepository;

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
        NTISS ntiss = ntissRepository.findAll().get(0);

        return (balls * ntiss.getColibrProc()) / ntiss.getColibrBall();
    }
}
