package br.com.halas;

import br.com.halas.client.ClientHttpConfiguration;
import br.com.halas.service.ShelterService;

import java.io.IOException;

public class RegisterShelterCommand implements Command {

    ClientHttpConfiguration clientHttpConfiguration = new ClientHttpConfiguration();

    ShelterService shelterService = new ShelterService(clientHttpConfiguration);

    @Override
    public void execute() {
        try {
            shelterService.register();
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
