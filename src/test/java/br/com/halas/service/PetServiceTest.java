package br.com.halas.service;

import br.com.halas.client.ClientHttpConfiguration;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class PetServiceTest {

    private ClientHttpConfiguration client = mock(ClientHttpConfiguration.class);
    private PetService petService = new PetService(client);
    private HttpResponse<String> response = mock(HttpResponse.class);

    @Test
    void importPets() throws IOException, InterruptedException {
        String userInput = String.format("Teste%spets.csv", System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        when(client.sendPost(anyString(), any())).thenReturn(response);

        petService.importPets();

        verify(client.sendPost(anyString(), anyString()), atLeast(1));
    }

}