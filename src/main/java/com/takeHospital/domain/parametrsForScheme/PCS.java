package com.takeHospital.domain.parametrsForScheme;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PCS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int colibrProc;

    public PCS() {
    }

    public PCS(int colibrProc) {
        this.colibrProc = colibrProc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getColibrProc() {
        return colibrProc;
    }

    public void setColibrProc(int colibrProc) {
        this.colibrProc = colibrProc;
    }
}
