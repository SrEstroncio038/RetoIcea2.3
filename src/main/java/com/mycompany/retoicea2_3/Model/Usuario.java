/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retoicea2_3.Model;

import java.time.LocalDate;

/**
 *
 * @author asola
 */
public abstract class Usuario {
    private String name;
    private String surname;
    private String city;
    private String pasword;
    private String user;
    private LocalDate birthDate;
    private Addres address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthPlace() {
        return city;
    }

    public void setBirthPlace(String birthPlace) {
        this.city = birthPlace;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Usuario() {
    }

    public Usuario(String name, String surname, String birthPlace, String pasword, String user, LocalDate birthDate, Addres address) {
        this.name = name;
        this.surname = surname;
        this.city = birthPlace;
        this.pasword = pasword;
        this.user = user;
        this.birthDate = birthDate;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Usuario{" + "name=" + name + ", surname=" + surname + ", birthPlace=" + city + ", pasword=" + pasword + ", user=" + user + ", birthDate=" + birthDate + '}';
    }
    
    
    
}
