package br.com.halas;

import java.util.Scanner;

public class AdopetConsoleApplication {

    public static void main(String[] args) {

        CommandExecutor commandExecutor = new CommandExecutor();

        System.out.println("##### BOAS VINDAS AO SISTEMA ADOPET CONSOLE #####");
        try {
            int optionChosen = 0;
            while (optionChosen != 5) {

                showMenu();

                String text = new Scanner(System.in).nextLine();
                optionChosen = Integer.parseInt(text);

                switch (optionChosen) {
                    case 1 -> commandExecutor.executeCommand(new ListShelterCommand());
                    case 2 -> commandExecutor.executeCommand(new RegisterShelterCommand());
                    case 3 -> commandExecutor.executeCommand(new ListPetCommand());
                    case 4 -> commandExecutor.executeCommand(new ImportPetsCommand());
                    case 5 -> System.exit(0);
                    default -> optionChosen = 0;
                }
            }

            System.out.println("Finalizando o programa...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showMenu() {
        System.out.println("\nDIGITE O NÚMERO DA OPERAÇÃO DESEJADA:");
        System.out.println("1 -> Listar abrigos cadastrados");
        System.out.println("2 -> Cadastrar novo abrigo");
        System.out.println("3 -> Listar pets do abrigo");
        System.out.println("4 -> Importar pets do abrigo");
        System.out.println("5 -> Sair");
    }

}
