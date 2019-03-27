import controllers.FileController;

import java.io.IOException;
import java.util.Scanner;

public class App {

    public static void main(String[] args){
        FileController fileController = new FileController();
        /*Scanner scanner = new Scanner(System.in);
        String [] parameters = new String[4];
        String [] labels = {"Insira a quantidade de lugares: ", "Insira a quantidade de transições: ", "Insira a quantidade de marcas: ", "Insira o peso de cada arco: "};
        int value;

        for(int i=0; i<4; i++){
            try {
                System.out.println(labels[i]);
                value = Integer.parseInt(scanner.nextLine());
                parameters[i] = Integer.toString(value);
            } catch (NumberFormatException e) {
                System.out.println("O valor inserido deve ser um número inteiro e positivo.");
                i--;
            }
        }

        try {
            fileController.writeToFile("test.txt", parameters);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        try {
            System.out.println(fileController.readFile("test.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }







    }
}
