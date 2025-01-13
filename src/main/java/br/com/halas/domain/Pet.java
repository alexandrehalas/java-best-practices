package br.com.halas.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pet {

    private Long id;

    @JsonProperty("tipo")
    private String type;
    @JsonProperty("nome")
    private String name;
    @JsonProperty("raca")
    private String breed;
    @JsonProperty("idade")
    private int age;
    @JsonProperty("cor")
    private String color;
    @JsonProperty("peso")
    private Float weight;

    public Pet() {

    }

    public Pet(String type, String name, String breed, int age, String color, Float weight) {
        this.type = type;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.color = color;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public String getColor() {
        return color;
    }

    public Float getWeight() {
        return weight;
    }
}
