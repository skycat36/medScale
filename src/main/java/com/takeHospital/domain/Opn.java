package com.takeHospital.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Opn")
public class Opn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "ОПН не может быть пустым")
    @Length(max = 50, message = "Name OPN too long")
    private String opn;                     //Отделене ОПН

    public Opn() {
    }

    public Opn(@NotBlank(message = "ОПН не может быть пустым") @Length(max = 50, message = "Name OPN too long") String opn) {
        this.opn = opn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpn() {
        return opn;
    }

    public void setOpn(String opn) {
        this.opn = opn;
    }
}
