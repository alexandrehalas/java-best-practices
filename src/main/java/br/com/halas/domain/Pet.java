package br.com.halas.domain;

public class Pet {

    private String type;
    private String name;
    private String breed;
    private int age;
    private String color;
    private Float weight;

    public Pet(String type, String name, String breed, int age, String color, Float weight) {
        this.type = type;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.color = color;
        this.weight = weight;
    }
}
