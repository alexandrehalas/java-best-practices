package br.com.halas.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Shelter {

    private Long id;

    @JsonProperty("nome")
    private String name;

    @JsonProperty("telefone")
    private String phone;

    private String email;
    private Pet[] pets;

    public Shelter() {

    }

    public Shelter(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Pet[] getPets() {
        return pets;
    }
}
