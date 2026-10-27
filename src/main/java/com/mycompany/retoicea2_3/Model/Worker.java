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
public class Worker extends Usuario{
    private int id;
    private Department department;
    private int seniority;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }

    public Worker() {
    }

    public Worker(int id, Department department, int seniority, String name, String surname, String birthPlace, String pasword, String user, LocalDate birthDate) {
        super(name, surname, birthPlace, pasword, user, birthDate);
        this.id = id;
        this.department = department;
        this.seniority = seniority;
    }

    @Override
    public String toString() {
        return "Worker{" + "id=" + id + ", department=" + department + ", seniority=" + seniority + '}';
    }
    
    
}
