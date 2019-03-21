package com.takeHospital.domain.parametrsForScheme;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SNAPPE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int colibrBall;

    private int colibrProc;

    public SNAPPE() {
    }

    public SNAPPE(int colibrBall, int colibrProc) {
        this.colibrBall = colibrBall;
        this.colibrProc = colibrProc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getColibrBall() {
        return colibrBall;
    }

    public void setColibrBall(int colibrBall) {
        this.colibrBall = colibrBall;
    }

    public int getColibrProc() {
        return colibrProc;
    }

    public void setColibrProc(int colibrProc) {
        this.colibrProc = colibrProc;
    }
}
