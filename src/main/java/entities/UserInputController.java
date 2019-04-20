package entities;

import controllers.CycleController;
import controllers.FileController;
import java.io.IOException;
import java.util.Scanner;

public class UserInputController {

    private Scanner scanner = new Scanner(System.in);

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
                System.out.println(CustomMessages.INVALID_OPTION_ERROR.getMessage());
                initializeProgram();
        }
    }

    private void importFromFile() throws IOException {
        FileController fileController = new FileController();
        String[] test = fileController.getLines(fileController.readFile("cenario6.txt"));
        CycleController cycleController = new CycleController(test[0], test[2], test[1]);
        cycleController.executeCycle();
        System.out.println(CustomMessages.DOWNLOAD_OPTION.getMessage());
        switch(Integer.parseInt(scanner.nextLine())){
            case 0:
                fileController.writeToFile("output.txt", cycleController.getOutputData());
                System.out.println(CustomMessages.DOWNLOAD_COMPLETED.getMessage());
                break;
            default:
                break;
        }
    }

    private void interactiveInput(){
        //TODO interactive input and validations
    }

    private void help() {
        System.out.println(CustomMessages.FILE_FORMAT.getMessage());
        System.out.println(CustomMessages.HELP_OPTION.getMessage());

        switch(Integer.parseInt(scanner.nextLine())){
            case 0:
                System.out.println(CustomMessages.FILE_EXAMPLE.getMessage());
                break;
            default:
                break;
        }
    }
}
