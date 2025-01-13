package br.com.halas.domain;

public class Shelter {

    private Long id;
    private String name;
    private String phone;
    private String email;

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
}
