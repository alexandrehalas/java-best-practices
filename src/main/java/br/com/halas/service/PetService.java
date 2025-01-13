package br.com.halas.service;

import br.com.halas.client.ClientHttpConfiguration;
import br.com.halas.domain.Pet;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PetService {

    private final ClientHttpConfiguration clientHttpConfiguration;

    public PetService(ClientHttpConfiguration clientHttpConfiguration) {
        this.clientHttpConfiguration = clientHttpConfiguration;
    }

    public boolean importPets() throws IOException, InterruptedException {
        System.out.println("Digite o id ou nome do abrigo:");
        String idOuNome = new Scanner(System.in).nextLine();

        System.out.println("Digite o nome do arquivo CSV:");
        String nomeArquivo = new Scanner(System.in).nextLine();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(nomeArquivo));
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " + nomeArquivo);
            return true;
        }
        String line;
        while ((line = reader.readLine()) != null) {
            String[] campos = line.split(",");
            String type = campos[0].toUpperCase();
            String name = campos[1];
            String breed = campos[2];
            int age = Integer.parseInt(campos[3]);
            String color = campos[4];
            Float weight = Float.parseFloat(campos[5]);

            Pet pet = new Pet(type, name, breed, age, color, weight);

            String uri = "http://localhost:8080/abrigos/" + idOuNome + "/pets";
            HttpResponse<String> response = clientHttpConfiguration.sendPost(uri, pet);

            int statusCode = response.statusCode();
            String responseBody = response.body();
            if (statusCode == 200) {
                System.out.println("Pet cadastrado com sucesso: " + name);
            } else if (statusCode == 404) {
                System.out.println("Id ou name do abrigo não encontado!");
                break;
            } else if (statusCode == 400 || statusCode == 500) {
                System.out.println("Erro ao cadastrar o pet: " + name);
                System.out.println(responseBody);
                break;
            }
        }
        reader.close();
        return false;
    }

    public boolean list() throws IOException, InterruptedException {
        System.out.println("Digite o id ou nome do abrigo:");
        String idOuNome = new Scanner(System.in).nextLine();

        String uri = "http://localhost:8080/abrigos/" + idOuNome + "/pets";
        HttpResponse<String> response = clientHttpConfiguration.sendGet(uri);
        int statusCode = response.statusCode();
        if (statusCode == 404 || statusCode == 500) {
            System.out.println("ID ou nome não cadastrado!");
            return true;
        }
        String responseBody = response.body();
        Pet[] pets = new ObjectMapper().readValue(responseBody, Pet[].class);
        List<Pet> petList = Arrays.stream(pets).toList();
        System.out.println("Pets cadastrados:");
        for (Pet pet : petList) {
            long id = pet.getId();
            String type = pet.getType();
            String name = pet.getName();
            String breed = pet.getBreed();
            int age = pet.getAge();
            System.out.println(id + " - " + type + " - " + name + " - " + breed + " - " + age + " ano(s)");
        }
        return false;
    }

}
