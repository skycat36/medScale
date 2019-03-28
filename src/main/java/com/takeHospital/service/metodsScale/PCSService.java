package com.takeHospital.service.metodsScale;

import com.takeHospital.domain.parametrsForScheme.ParamScheme;
import com.takeHospital.repository.SchemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class PCSService implements MedScale {

    private static final int halfMonth = 182;
    private static final int oneYear = 365;

    @Autowired
    private SchemeRepository schemeRepository;

    private int countBalls;
    private int countBallsForAge;

    public PCSService() {
    }

    public PCSService(int countBalls, LocalDate bornDate, LocalDate thisDate) {
        this.countBalls = countBalls;
        setThisDateAndBorn(bornDate, thisDate);
    }

    public void setThisDateAndBorn(LocalDate bornDate, LocalDate thisDate){
        //Period period = Period.between(thisDate, bornDate);
        //Duration duration = Duration.from(period.);
        Long countDay = ChronoUnit.DAYS.between(bornDate, thisDate);
        if (countDay < halfMonth){
            this.countBallsForAge = 9;
        }
        if (countDay >= halfMonth && countDay < oneYear){
            this.countBallsForAge = 11;
        }
        if (countDay >= oneYear && countDay < oneYear * 2){
            this.countBallsForAge = 12;
        }
        if (countDay >= oneYear * 2 && countDay < oneYear * 5){
            this.countBallsForAge = 13;
        }
        if (countDay >= oneYear * 5){
            this.countBallsForAge = 14;
        }
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
        ParamScheme pcs = schemeRepository.findByNameSheme("pcs");
        return (pcs.getColibrProc() * balls) / countBallsForAge;
    }
}
