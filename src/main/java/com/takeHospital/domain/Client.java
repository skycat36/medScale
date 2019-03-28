package com.takeHospital.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Поле фамилия не может быть пустым")
    @Length(max = 50, message = "Family too long")
    private String fam;                     //Фамилия

    @NotBlank(message = "Поле имя не может быть пустым")
    @Length(max = 50, message = "Name too long")
    private String name;                    //Имя

    @NotBlank(message = "Поле отчество не может быть пустым")
    @Length(max = 50, message = "Second name too long")
    private String secName;                 //Отчество

    private LocalDate birthdate;            //Дата рождения

    private LocalDate dateOfArrival;        //Дата прибытия


    private LocalDate survayDate;           //Дата обследования


    private LocalDate dateOfDeparture;      //Дата выписки


    private LocalDate dateOfDeath;          //Дата смерти

    private Long opn;                        //Отделене ОПН

    private Integer crib2;                  //Оценка острого состояния новорожденного. Результат в балах.
    private Integer snapPe;                 //Перенатальная шкала острого состояния новорожденного. Результат в балах.
    private Integer ntiss;                  //Неонатальная шкала эфективности лечения. Результат в балах.
    private Integer pcs;                    //Педиатрическая шкала комы Simpson and Reilly. Результат в балах.
    private Integer trips;                  //Транспортный индекс риска физиологической стабильности новорожденного. Результат в балах.
    private Integer sofa;                   //Динамическая оценка органной недостаточности. Результат в балах.

    public Client() {
    }

    public Client(@NotBlank(message = "Поле фамилия не может быть пустым") @Length(max = 50, message = "Family too long") String fam, @NotBlank(message = "Поле имя не может быть пустым") @Length(max = 50, message = "Name too long") String name, @NotBlank(message = "Поле отчество не может быть пустым") @Length(max = 50, message = "Second name too long") String secName, LocalDate birthdate, LocalDate dateOfArrival, LocalDate survayDate, LocalDate dateOfDeparture, LocalDate dateOfDeath, Long opn, Integer crib2, Integer snapPe, Integer ntiss, Integer pcs, Integer trips, Integer sofa) {
        this.fam = fam;
        this.name = name;
        this.secName = secName;
        this.birthdate = birthdate;
        this.dateOfArrival = dateOfArrival;
        this.survayDate = survayDate;
        this.dateOfDeparture = dateOfDeparture;
        this.dateOfDeath = dateOfDeath;
        this.opn = opn;
        this.crib2 = crib2;
        this.snapPe = snapPe;
        this.ntiss = ntiss;
        this.pcs = pcs;
        this.trips = trips;
        this.sofa = sofa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFam() {
        return fam;
    }

    public void setFam(String fam) {
        this.fam = fam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public LocalDate getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(LocalDate dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public LocalDate getSurvayDate() {
        return survayDate;
    }

    public void setSurvayDate(LocalDate survayDate) {
        this.survayDate = survayDate;
    }

    public LocalDate getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(LocalDate dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public LocalDate getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(LocalDate dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public Long getOpn() {
        return opn;
    }

    public void setOpn(Long opn) {
        this.opn = opn;
    }

    public Integer getCrib2() {
        return crib2;
    }

    public void setCrib2(Integer crib2) {
        this.crib2 = crib2;
    }

    public Integer getSnapPe() {
        return snapPe;
    }

    public void setSnapPe(Integer snapPe) {
        this.snapPe = snapPe;
    }

    public Integer getNtiss() {
        return ntiss;
    }

    public void setNtiss(Integer ntiss) {
        this.ntiss = ntiss;
    }

    public Integer getPcs() {
        return pcs;
    }

    public void setPcs(Integer pcs) {
        this.pcs = pcs;
    }

    public Integer getTrips() {
        return trips;
    }

    public void setTrips(Integer trips) {
        this.trips = trips;
    }

    public Integer getSofa() {
        return sofa;
    }

    public void setSofa(Integer sofa) {
        this.sofa = sofa;
    }
}
