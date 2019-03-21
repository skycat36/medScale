package com.takeHospital.service.metodsScale;

import com.takeHospital.domain.exeptions.SchemeExeption;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CRIB2Service implements MedScale {

    private int countBalls;

    public CRIB2Service() {
    }

    public CRIB2Service(int countBalls, double temp, int gestation, double weight, double maxBE, String selSex) throws SchemeExeption {
        this.countBalls = countBalls;

        if (temp <= 29.6) { this.countBalls += 5; }
        if (temp >= 29.7 && temp <= 31.2) { this.countBalls += 4; }
        if (temp >= 31.3 && temp <= 32.8) { this.countBalls += 3; }
        if (temp >= 32.9 && temp <= 34.4) { this.countBalls += 2; }
        if (temp >= 34.5 && temp <= 36)   { this.countBalls += 1; }
        if (temp >= 36.1 && temp <= 37.5) { this.countBalls += 0; }
        if (temp >= 37.6 && temp <= 39.1) { this.countBalls += 1; }
        if (temp >= 39.2 && temp <= 40.7) { this.countBalls += 2; }
        if (temp >= 40.8)                 { this.countBalls += 3; }

        if (maxBE < -26) { this.countBalls += 7; }
        if (maxBE >= -26 && maxBE <= -23) { this.countBalls += 6; }
        if (maxBE >= -22 && maxBE <= -18) { this.countBalls += 5; }
        if (maxBE >= -17 && maxBE <= -13) { this.countBalls += 4; }
        if (maxBE >= -12 && maxBE <= -8)  { this.countBalls += 3; }
        if (maxBE >= -7 && maxBE <= -3)   { this.countBalls += 2; }
        if (maxBE >= -2 && maxBE <= 2)    { this.countBalls += 1; }
        if (maxBE >= 3)                   { this.countBalls += 0; }

        Map<Integer, List<Integer>> listMap = new HashMap<>();
        if (selSex.equals("male")) {
            listMap.put(22, Arrays.asList(15, 14));
            listMap.put(23, Arrays.asList(14, 13, 12, 12));
            listMap.put(24, Arrays.asList(13, 12, 11, 10));
            listMap.put(25, Arrays.asList(12, 11, 10, 9));
            listMap.put(26, Arrays.asList(11, 10, 8, 8, 8));
            listMap.put(27, Arrays.asList(10, 9, 7, 7, 6, 6));
            listMap.put(28, Arrays.asList(10, 8, 7, 6, 5, 5));
            listMap.put(29, Arrays.asList(0, 8, 6, 5, 3, 3, 3));
            listMap.put(30, Arrays.asList(0, 8, 6, 4, 3, 2, 1, 2, 3));
            listMap.put(31, Arrays.asList(0, 8, 6, 3, 2, 1, 0, 0, 0, 1));
            listMap.put(32, Arrays.asList(0, 0, 6, 3, 1, 0, 0, 0, 0, 0, 0));
        } else {
            listMap.put(22, Arrays.asList(14, 13));
            listMap.put(23, Arrays.asList(13, 12, 11, 11));
            listMap.put(24, Arrays.asList(12, 11, 10, 10));
            listMap.put(25, Arrays.asList(11, 10, 9, 8));
            listMap.put(26, Arrays.asList(11, 9, 8, 7, 7));
            listMap.put(27, Arrays.asList(10, 8, 7, 6, 5, 6));
            listMap.put(28, Arrays.asList(10, 8, 6, 5, 4, 4));
            listMap.put(29, Arrays.asList(0, 7, 5, 4, 3, 3, 3));
            listMap.put(30, Arrays.asList(0, 7, 5, 3, 2, 1, 1, 1, 2));
            listMap.put(31, Arrays.asList(0, 7, 5, 3, 1, 0, 0, 0, 0, 1));
            listMap.put(32, Arrays.asList(0, 0, 5, 3, 1, 0, 0, 0, 0, 0, 0));
        }

        try {
            if (weight <= 500) {
                this.countBalls += listMap.get(gestation).get(0);
            }
            if (weight >= 501 && weight <= 750) {
                this.countBalls += listMap.get(gestation).get(1);
            }
            if (weight >= 751 && weight <= 1000) {
                this.countBalls += listMap.get(gestation).get(2);
            }
            if (weight >= 1001 && weight <= 1250) {
                this.countBalls += listMap.get(gestation).get(3);
            }
            if (weight >= 1251 && weight <= 1500) {
                this.countBalls += listMap.get(gestation).get(4);
            }
            if (weight >= 1501 && weight <= 1750) {
                this.countBalls += listMap.get(gestation).get(5);
            }
            if (weight >= 1751 && weight <= 2000) {
                this.countBalls += listMap.get(gestation).get(6);
            }
            if (weight >= 2001 && weight <= 2250) {
                this.countBalls += listMap.get(gestation).get(7);
            }
            if (weight >= 2251 && weight <= 2500) {
                this.countBalls += listMap.get(gestation).get(8);
            }
            if (weight >= 2501 && weight <= 2750) {
                this.countBalls += listMap.get(gestation).get(9);
            }
            if (weight >= 2751 && weight <= 3000) {
                this.countBalls += listMap.get(gestation).get(10);
            }
        }catch (Exception ex){
            throw new SchemeExeption("Вес не соответствует сроку гестации");
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
        double logit = -6.476 + 0.45 * balls;
        double ochc = Math.exp(logit) / (1 + Math.exp(logit));
        return Integer.parseInt(String.valueOf(Math.round(Math.exp(ochc) / (1 + Math.exp(ochc)) * 100)));
    }
}
