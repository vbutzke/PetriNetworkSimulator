package controllers;

import entities.Arc;
import entities.Cycle;
import entities.Transition;

import java.util.Hashtable;
import java.util.LinkedList;

public class CycleController {

    private Cycle cycle;
     //Lugar e marca
    //L1 M1, L2 M2, ...
    //Transições
    //T1, T2, T3 ...
    //Arcos
    //AN L1 T1, AN T1 L1

     public String executeCycle(){
         cycle.nextStep();
         LinkedList<Transition> transitions = cycle.getTransitions();
         Hashtable<String, Integer> places = cycle.getPlaces();
         String output = "";

         for (int i=0; i<transitions.size(); i++){
             places = transitions.get(i).execute(places);
         }
         //
         return buildOutput();
     }

     private String buildOutput(){
         return "";
     }
    //verify
    //create
    //search
    //execute

}
