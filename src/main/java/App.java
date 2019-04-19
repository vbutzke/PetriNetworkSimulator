import controllers.CycleController;
import controllers.FileController;
import entities.Arc;
import entities.Cycle;
import entities.Transition;
import entities.UserInputController;

import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;

public class App {

    public static void main(String[] args){

        UserInputController userInputController = new UserInputController();

        try {
            userInputController.initializeProgram();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
