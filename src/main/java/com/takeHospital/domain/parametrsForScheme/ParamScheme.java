package com.takeHospital.domain.parametrsForScheme;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "param_scheme")
public class ParamScheme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название схемы")
    @Length(max = 20, message = "Name scheme to long")
    private String nameSheme;

    private int colibrBall;

    private int colibrProc;

    public ParamScheme() {
    }

    public ParamScheme(@NotBlank(message = "Название схемы") @Length(max = 20, message = "Name scheme to long") String nameSheme, int colibrBall, int colibrProc) {
        this.nameSheme = nameSheme;
        this.colibrBall = colibrBall;
        this.colibrProc = colibrProc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameSheme() {
        return nameSheme;
    }

    public void setNameSheme(String nameSheme) {
        this.nameSheme = nameSheme;
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
