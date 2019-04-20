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
        String[] test = fileController.getLines(fileController.readFile("cenario3.txt"));
        CycleController cycleController = new CycleController(test[0], test[2], test[1]);
        cycleController.executeCycle();
        System.out.println(CustomMessages.DOWNLOAD_OPTION.getMessage());
        switch(Integer.parseInt(scanner.nextLine())){
            case 0:
                fileController.writeToFile("output.csv", cycleController.getOutputData());
                System.out.println(CustomMessages.DOWNLOAD_COMPLETED.getMessage());
                break;
            default:
                break;
        }
    }

    private void interactiveInput(){

        do{
            System.out.println(CustomMessages.PLACES_INPUT.getMessage());
        } while (!validatePlacesInput(scanner.nextLine()));

        do{
            System.out.println(CustomMessages.ARCS_INPUT.getMessage());
        }
        while(!validateArcsInput(scanner.nextLine()));

        do {
            System.out.println(CustomMessages.TRANSITIONS_INPUT.getMessage());
        } while(!validateTransitionsInput(scanner.nextLine()));

        System.out.println(CustomMessages.INPUT_MESSAGE.getMessage());
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

    private boolean validatePlacesInput(String input){
        return validate(input, "L", CustomMessages.ERROR_TRANSITIONS_INPUT.getMessage(), ",");
    }

    private boolean validateArcsInput(String input){

        String [] arcs = input.split(",");

        for (String a: arcs) {
            String [] values = a.split(" ");

            if(!validate(values[1], "L", CustomMessages.ERROR_ARCS_INPUT.getMessage(), "") ||
               !validate(values[2], "T", CustomMessages.ERROR_ARCS_INPUT.getMessage(), "") ||
               !validate(values[0]+values[3], "A", CustomMessages.ERROR_ARCS_INPUT.getMessage(), "")){
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
            if(t.startsWith(prefix) && !isSuffixAnumber(t.substring(1))){
                System.out.println(error);
                return false;
            }
        }
        return true;
    }
}
