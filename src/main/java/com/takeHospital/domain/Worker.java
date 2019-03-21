package com.takeHospital.domain;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Entity
public class Worker implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Поле Фамилия не может быть пустым")
    @Length(max = 50, message = "Family too long")
    private String fam;         //Фамилия

    @NotBlank(message = "Поле Имя не может быть пустым")
    @Length(max = 50, message = "Family too long")
    private String name;        //Имя

    @NotBlank(message = "Поле Отчество не может быть пустым")
    @Length(max = 50, message = "Family too long")
    private String secName;     //Отчество

    @NotBlank(message = "Поле Должность не может быть пустым")
    @Length(max = 100, message = "Family too long")
    private String position;    //Должность


    @NotBlank(message = "Поле Логин не может быть пустым")
    @Column(nullable = false, unique = true)
    @Length(max = 50, message = "Login too long")
    private String login;       //Логин

    @NotBlank(message = "Поле Пароль не может быть пустым")
    @Length(max = 50, message = "Password too long")
    private String password;    //Пароль

    public Worker() {
    }

    public Worker(Long id, String fam, String name, String secName, String position, String login, String password) {
        this.id = id;
        this.fam = fam;
        this.name = name;
        this.secName = secName;
        this.position = position;
        this.login = login;
        this.password = password;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
