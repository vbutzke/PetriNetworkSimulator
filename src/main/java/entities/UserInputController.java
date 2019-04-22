package entities;

import controllers.CycleController;
import controllers.FileController;
import java.io.IOException;
import java.util.Scanner;

public class UserInputController {

    private Scanner scanner = new Scanner(System.in);
    private FileController fileController = new FileController();

    public void initializeProgram() throws IOException {
        System.out.println(CustomMessages.INIT_PROGRAM.getMessage());
        switch (Integer.parseInt(scanner.nextLine())){
            case 1:
                System.out.println(CustomMessages.FILE_PATH.getMessage());
                execute(importFromFile(scanner.nextLine()));
                initializeProgram();
                break;
            case 2:
                execute(interactiveInput());
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

    private String[] importFromFile(String filePath) throws IOException {
        return fileController.getLines(fileController.readFile(filePath));
    }

    private String[] interactiveInput(){

        String[] input = new String[3];

        do{
            System.out.println(CustomMessages.PLACES_INPUT.getMessage());
            input[0] = scanner.nextLine();
        } while (!validatePlacesInput(input[0]));

        do{
            System.out.println(CustomMessages.ARCS_INPUT.getMessage());
            input[1] = scanner.nextLine();
        }
        while(!validateArcsInput(input[1]));

        do {
            System.out.println(CustomMessages.TRANSITIONS_INPUT.getMessage());
            input[2] = scanner.nextLine();
        } while(!validateTransitionsInput(input[2]));

        System.out.println(CustomMessages.INPUT_MESSAGE.getMessage());
        return input;
    }

    private void help() {
        System.out.println(CustomMessages.FILE_FORMAT.getMessage());
        System.out.println(CustomMessages.HELP_OPTION.getMessage());

        if (Integer.parseInt(scanner.nextLine()) == 0) {
            System.out.println(CustomMessages.FILE_EXAMPLE.getMessage());
        }
    }

    private boolean validatePlacesInput(String input){
        return validate(input, "L", CustomMessages.ERROR_PLACES_INPUT.getMessage(), ",");
    }

    private boolean validateArcsInput(String input){

        String [] arcs = input.split(",");

        for (String a: arcs) {
            String [] values = a.split(" ");

            if(!(validate(values[0]+values[3], "A", CustomMessages.ERROR_ARCS_INPUT.getMessage(), " ") &&
                 (validatePlacesInput(values[1]) && validateTransitionsInput(values[2]) ||
                  validatePlacesInput(values[2]) && validateTransitionsInput(values[1])))){

                return false;
            }

        }

        return true;
    }

    private boolean validateTransitionsInput(String input){
        return validate(input, "T", CustomMessages.ERROR_TRANSITIONS_INPUT.getMessage(), ",");
    }

    private boolean isSuffixAnumber(String suffix){
        try{
            Integer.parseInt(suffix);
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    private boolean validate(String input, String prefix, String error, String regex){
        String[] token = input.split(regex);
        for (String t:token) {
            t = t.replaceAll(" ", "");
            if(t.startsWith(prefix) && !isSuffixAnumber(t.substring(1))){
                System.out.println(error);
                return false;
            }
        }
        return true;
    }

    private void execute(String [] input) throws IOException {
        CycleController cycleController = new CycleController(input[0], input[2], input[1]);
        cycleController.executeCycle();
        System.out.println(CustomMessages.DOWNLOAD_OPTION.getMessage());
        if (Integer.parseInt(scanner.nextLine()) == 0) {
            fileController.writeToFile("output.csv", cycleController.getOutputData());
            System.out.println(CustomMessages.DOWNLOAD_COMPLETED.getMessage());
        }
    }
}
