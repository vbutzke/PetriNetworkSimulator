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
                break;
            case 2:
                interactiveInput();
                break;
            case 3:
                help();
                break;
            default:
                System.out.println("A opção selecionada não é válida. Por favor selecione 1, 2 ou 3");
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

    private void help(){
        System.out.println(CustomMessages.FILE_FORMAT.getMessage());
        System.out.println("Digite 0 para um exemplo de arquivo");
        if(Integer.parseInt(scanner.nextLine()) == 0){
            System.out.println(CustomMessages.FILE_EXAMPLE.getMessage());
        }
    }
}
