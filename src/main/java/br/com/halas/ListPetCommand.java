package br.com.halas;

import br.com.halas.client.ClientHttpConfiguration;
import br.com.halas.service.PetService;

import java.io.IOException;

public class ListPetCommand implements Command {

    ClientHttpConfiguration clientHttpConfiguration = new ClientHttpConfiguration();

    PetService petService = new PetService(clientHttpConfiguration);

    @Override
    public void execute() {
        try {
            petService.list();
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
