package controllers;

import entities.Cycle;
import entities.Transition;
import java.util.Hashtable;
import java.util.LinkedList;

public class CycleController {
     private Cycle cycle;
     private LinkedList<String> outputData;

     public CycleController(String placesLine, String transitionsLine, String arcsLine){
        cycle = new Cycle(placesLine, transitionsLine, arcsLine);
        outputData = new LinkedList<>();
     }

     public void interactiveInput(String placesLine, String transitionsLine, String arcsLine){
        cycle.interactiveInput(placesLine, transitionsLine, arcsLine);
     }

     public void executeCycle(){
         cycle.execute();
        // cycle.printCycle(); //TODO remover essa linha
         buildOutput();
         printOutput();
     }

     private void buildOutput(){
         String header = " | Passos | ";
         String line = " | " + cycle.getStep() + " | ";
         Hashtable<String, Integer> places  = cycle.getPlaces();
         LinkedList<Transition> transitions = cycle.getTransitions();

         for (String key : places.keySet()) {
            header = header + key + " | ";
            line = line + places.get(key) + " | ";
         }

         for (Transition transition : transitions) {
             header = header + transition.getName() + " | "; //TODO formatar output
             line = line + transition.isEnabled() + " | ";
         }
         outputData.add(0, header);
         outputData.add(line); //TODO testar com mais de uma linha de output pra ver o que acontece

     }

     private void printOutput(){
         for (String line: outputData) {
             System.out.println(line);
         }
     }

}
