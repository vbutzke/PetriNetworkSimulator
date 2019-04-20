package entities;

import controllers.CycleController;
import controllers.FileController;
import java.io.IOException;
import java.util.Scanner;

public class UserInputController {

    Scanner scanner = new Scanner(System.in);

    public void initializeProgram() throws IOException {
        System.out.println(CustomMessages.INIT_PROGRAM.getMessage());
        switch (Integer.parseInt(scanner.nextLine())){
            case 1:
                importFromFile();
                initializeProgram();
                break;
            case 2:
                interactiveInput();
                break;
            case 3:
                help();
                initializeProgram();
                break;
            case 4:
                break;
            default:
                System.out.println("A opção selecionada não é válida. Por favor selecione 1, 2, 3 ou 4");
                initializeProgram();
        }
    }

    private void importFromFile() throws IOException {
        FileController fileController = new FileController();
        String[] test = fileController.getLines(fileController.readFile("test.txt"));
        CycleController cycleController = new CycleController(test[0], test[2], test[1]);
        cycleController.executeCycle();
    }

    private void interactiveInput(){

    }

    private void help() {
        System.out.println(CustomMessages.FILE_FORMAT.getMessage());
        System.out.println("Digite 0 para um exemplo de arquivo, ou qualquer outro número para continuar");

        switch(Integer.parseInt(scanner.nextLine())){
            case 0:
                System.out.println(CustomMessages.FILE_EXAMPLE.getMessage());
                break;
            default:
                break;
        }
    }
}
