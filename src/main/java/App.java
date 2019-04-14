import controllers.CycleController;
import controllers.FileController;
import entities.Arc;
import entities.Cycle;
import entities.Transition;

import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;

public class App {

    public static void main(String[] args){
        FileController fileController = new FileController();
        try {
            String[] test = fileController.getLines(fileController.readFile("test.txt"));
            CycleController cycleController = new CycleController(test[0], test[2], test[1]);
            cycleController.executeCycle();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*Scanner scanner = new Scanner(System.in);
        String [] parameters = new String[4];
        String [] labels = {"Insira a quantidade de lugares: ", "Insira a quantidade de transições: ", "Insira a quantidade de marcas: ", "Insira o peso de cada arco: "};
        int value;
        String trackingTest = "";
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

        //try {
        //    String[] test = fileController.getLines(fileController.readFile("test.txt"));
//
        //    for (String t: test) {
        //        System.out.println("Linha: "+ t);
        //    }
//
//
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
        //Lugar e marca
        //L1 M1, L2 M2, ...
        //Transições
        //T1, T2, T3 ...
        //Arcos
        //AN L1 T1, AN T1 L1

    }

}
