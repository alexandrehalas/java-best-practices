package br.com.halas;

import java.util.Scanner;

public class AdopetConsoleApplication {

    public static void main(String[] args) {

        CommandExecutor commandExecutor = new CommandExecutor();

        System.out.println("##### BOAS VINDAS AO SISTEMA ADOPET CONSOLE #####");
        try {
            int opcaoEscolhida = 0;
            while (opcaoEscolhida != 5) {
                System.out.println("\nDIGITE O NÚMERO DA OPERAÇÃO DESEJADA:");
                System.out.println("1 -> Listar abrigos cadastrados");
                System.out.println("2 -> Cadastrar novo abrigo");
                System.out.println("3 -> Listar pets do abrigo");
                System.out.println("4 -> Importar pets do abrigo");
                System.out.println("5 -> Sair");

                String textoDigitado = new Scanner(System.in).nextLine();
                opcaoEscolhida = Integer.parseInt(textoDigitado);

                if (opcaoEscolhida == 1) {
                    commandExecutor.executeCommand(new ListShelterCommand());
                } else if (opcaoEscolhida == 2) {
                    commandExecutor.executeCommand(new RegisterShelterCommand());
                } else if (opcaoEscolhida == 3) {
                    commandExecutor.executeCommand(new ListPetCommand());
                } else if (opcaoEscolhida == 4) {
                    commandExecutor.executeCommand(new ImportPetsCommand());
                } else if (opcaoEscolhida == 5) {
                    break;
                } else {
                    System.out.println("NÚMERO INVÁLIDO!");
                    opcaoEscolhida = 0;
                }
            }
            System.out.println("Finalizando o programa...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
