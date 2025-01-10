package br.com.halas.service;

import br.com.halas.client.ClientHttpConfiguration;
import br.com.halas.domain.Shelter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.http.HttpResponse;
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
        JsonArray jsonArray = JsonParser.parseString(responseBody).getAsJsonArray();
        System.out.println("Abrigos cadastrados:");
        for (JsonElement element : jsonArray) {
            JsonObject jsonObject = element.getAsJsonObject();
            long id = jsonObject.get("id").getAsLong();
            String nome = jsonObject.get("nome").getAsString();
            System.out.println(id + " - " + nome);
        }
    }


}
