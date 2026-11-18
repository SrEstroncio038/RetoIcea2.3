package com.mycompany.retoicea2_3.Model;

import java.time.LocalDate;

public class Admin extends Usuario {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Admin() {
    }

    public Admin(String name, String surname, String birthPlace, String pasword, String user, LocalDate birthDate, Addres address) {
        super(name, surname, birthPlace, pasword, user, birthDate, address);
    }

    @Override
    public String toString() {
        return "Admin{" + "id=" + id + '}';
    }

}
