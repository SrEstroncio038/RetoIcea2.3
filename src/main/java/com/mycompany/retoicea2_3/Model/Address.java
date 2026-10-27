/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retoicea2_3.Model;

/**
 *
 * @author asola
 */
class Address {
    private int houseNumber;
    private String name;
    private int floor;
    private char letter;

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public Address(int houseNumber, String name, int floor, char letter) {
        this.houseNumber = houseNumber;
        this.name = name;
        this.floor = floor;
        this.letter = letter;
    }

    public Address() {
    }
    
    @Override
    public String toString() {
        return "Address{" + "houseNumber=" + houseNumber + ", name=" + name + ", floor=" + floor + ", letter=" + letter + '}';
    }
    
    
}
