/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retoicea2_3.Model;

/**
 *
 * @author asola
 */
public class Characteristics {
    private String color;
    private float weight;
    private float height;
    private float width;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "Characteristics{" + "color=" + color + ", weight=" + weight + ", height=" + height + ", width=" + width + '}';
    }

    public Characteristics(String color, float weight, float height, float width) {
        this.color = color;
        this.weight = weight;
        this.height = height;
        this.width = width;
    }

    public Characteristics() {
    }
    
    
}
