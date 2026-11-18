package com.mycompany.retoicea2_3.Model;

import java.time.LocalDate;

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
    
    public String getPassword() {
        return super.getPassword();
    }

    public void setPassword(String password) {
        super.setPassword(password);
    }

    public Worker() {
    }

    public Worker(Department department, String name, String surname, String birthPlace, String pasword, String user, LocalDate birthDate, Addres address) {
        super(name, surname, birthPlace, pasword, user, birthDate, address);
        this.department = department;
        this.seniority = 0;
    }

    @Override
    public String toString() {
        return "Worker{" + "id=" + id + ", department=" + department + ", seniority=" + seniority + '}';
    }
    
    
}
