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
        outputData.add(0, "");
     }

     public void interactiveInput(String placesLine, String transitionsLine, String arcsLine){
        cycle.interactiveInput(placesLine, transitionsLine, arcsLine);
     }

     public void executeCycle(){
         buildOutput();
         cycle.execute();
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
             header = header + transition.getName() + " | ";
             line = line + transition.isEnabled() + " | ";
         }

         outputData.set(0, header);
         outputData.add(line);
     }

     private void printOutput(){
         for (String line: outputData) {
             System.out.println(line);
         }
     }

     public String[] getOutputData(){
         String[] output =  new String[outputData.size()];
         for (int i=0; i< outputData.size(); i++){
            output[i] = outputData.get(i).replaceAll("\\|", "~");
         }
         return output;
     }

}
