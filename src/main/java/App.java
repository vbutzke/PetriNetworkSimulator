import entities.UserInputController;
import java.io.IOException;

public class App {

    public static void main(String[] args){

        UserInputController userInputController = new UserInputController();

        try {
            userInputController.initializeProgram();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO tratamento de erros
        //TODO run code inspector
        //TODO remove unused code
    }

}
