package com.takeHospital.service.metodsScale;

import com.takeHospital.repository.SchemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TRIPSService implements MedScale {

    @Autowired
    private SchemeRepository schemeRepository;

    private int countBalls;

    public TRIPSService(){}

    public TRIPSService(int countBalls) {
        this.countBalls = countBalls;
    }

    @Override
    public int getCountBalls() {
        return this.countBalls;
    }

    @Override
    public int getCountProcLethalOutcome(int balls) {

        if (balls <= 7){
            return 1;
        }

        if (balls >= 8 && balls <= 16){
            return 3;
        }

        if (balls >= 17 && balls <= 23){
            return 5;
        }

        if (balls >= 24 && balls <= 30){
            return 15;
        }

        if (balls >= 31 && balls <= 38){
            return 18;
        }

        if (balls >= 39){
            return 27;
        }
        return 0;
    }
}
