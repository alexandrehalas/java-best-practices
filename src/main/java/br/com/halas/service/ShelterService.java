package br.com.halas.service;

import br.com.halas.client.ClientHttpConfiguration;
import br.com.halas.domain.Shelter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ShelterService {

    private final ClientHttpConfiguration clientHttpConfiguration;

    public ShelterService(ClientHttpConfiguration clientHttpConfiguration) {
        this.clientHttpConfiguration = clientHttpConfiguration;
    }

    public void register() throws IOException, InterruptedException {
        System.out.println("Digite o name do abrigo:");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Digite o phone do abrigo:");
        String phone = new Scanner(System.in).nextLine();
        System.out.println("Digite o email do abrigo:");
        String email = new Scanner(System.in).nextLine();

        Shelter shelter = new Shelter(name, phone, email);
        String uri = "http://localhost:8080/abrigos";

        HttpResponse<String> response = clientHttpConfiguration.dispararRequisicaoPost(uri, shelter);
        int statusCode = response.statusCode();
        String responseBody = response.body();
        if (statusCode == 200) {
            System.out.println("Abrigo cadastrado com sucesso!");
            System.out.println(responseBody);
        } else if (statusCode == 400 || statusCode == 500) {
            System.out.println("Erro ao cadastrar o abrigo:");
            System.out.println(responseBody);
        }
    }

    public void list() throws IOException, InterruptedException {
        String uri = "http://localhost:8080/abrigos";
        HttpResponse<String> response = clientHttpConfiguration.dispararRequisicaoGet(uri);
        String responseBody = response.body();
        Shelter[] shelters = new ObjectMapper().readValue(responseBody, Shelter[].class);
        List<Shelter> shelterList = Arrays.stream(shelters).toList();
        System.out.println("Abrigos cadastrados:");
        for (Shelter shelter : shelterList) {
            long id = shelter.getId();
            String nome = shelter.getName();
            System.out.println(id + " - " + nome);
        }
    }


}
