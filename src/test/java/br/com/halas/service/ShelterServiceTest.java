package br.com.halas.service;

import br.com.halas.client.ClientHttpConfiguration;
import br.com.halas.domain.Shelter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShelterServiceTest {

    private ClientHttpConfiguration client = mock(ClientHttpConfiguration.class);
    private ShelterService shelterService = new ShelterService(client);
    private HttpResponse<String> response = mock(HttpResponse.class);
    private Shelter shelter = new Shelter("aaa", "(41)99999-9999", "aaa@email.com");

    @Test
    void verifyIfClientSendGetIsCalled() throws IOException, InterruptedException {

        shelter.setId(0L);
        String expectedShelters = "Abrigos cadastrados:";
        String expectedIdAndName = "0 - aaa";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        when(response.body()).thenReturn("""
                [{"id": 0, "nome": "aaa", "telefone": "(41)99999-9999", "email": "aaa@gmail.com"}]
        """);
        when(client.sendGet(anyString())).thenReturn(response);

        shelterService.list();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actualShelters = lines[0];
        String actualIdAndName = lines[1];

        Assertions.assertEquals(expectedShelters, actualShelters);
        Assertions.assertEquals(expectedIdAndName, actualIdAndName);
    }

    @Test
    void deveVerificarQuandoNaoHaAbrigo() throws IOException, InterruptedException {
        shelter.setId(0L);
        String expected = "Não há abrigos cadastrados";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        when(response.body()).thenReturn("[]");
        when(client.sendGet(anyString())).thenReturn(response);

        shelterService.list();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[0];

        Assertions.assertEquals(expected, actual);
    }
}