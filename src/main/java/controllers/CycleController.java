package controllers;

import entities.Arc;
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

     public void executeCycle(){
         buildHeader();
         execute();
         printOutput();
     }

     private void buildHeader(){
         String header = " | Passos | ";
         Hashtable<String, Integer> places  = cycle.getPlaces();
         LinkedList<Transition> transitions = cycle.getTransitions();

         for (String key : places.keySet()) {
            header = header + key + " | ";
         }

         for (Transition transition : transitions) {
             header = header + transition.getName() + " | ";
         }

         outputData.set(0, header);
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

     private void execute(){
        LinkedList<Arc> unselectedArcs; // pego arcos que NÃO devem ser executados
        boolean isArcOnTransition;
        int enabledTransitions = 0;
        int transitionsSize = cycle.getTransitions().size();
        Transition transition;

        outputData.add(cycle.getOutput());
        do {
            unselectedArcs = mapAndSolveConflicts(); // pego os arcos que NÃO devem ser executados

            for (int i = 0; i < transitionsSize; i++) {

                transition = cycle.getTransitions().get(i);

                isArcOnTransition = false;
                for (Arc a : unselectedArcs) {
                    if (isArcOnTransition(transition, a)) { //se o algum arco não selecionado está na transição atual
                        isArcOnTransition = true;
                        break;
                    }
                }

                if (!isArcOnTransition) { //se não tenho conflitos nos arcos da transição, executo ela
                    cycle.setPlaces(transition.execute(cycle.getPlaces()));
                } else {
                    enabledTransitions--;
                }//número de conflitos é meu número de transições erroneamente habilitadas
            }

            for(int j=0; j<transitionsSize; j++){
                transition = cycle.getTransitions().get(j);
                transition.setEnabled(cycle.getPlaces());

                if(transition.isEnabled()) {
                    enabledTransitions++;
                } else {
                    enabledTransitions = (enabledTransitions>0) ? enabledTransitions-1 : 0;
                }
            }
            cycle.nextStep();
            outputData.add(cycle.getOutput());
        } while(enabledTransitions>0); //até que nenhum arco possa mais ser executado

     }

     private boolean isArcOnTransition(Transition transition, Arc arc){
        for (Arc transArc: transition.getInArcs()) {
            if (transArc == arc)
                return true;
        }

        return false;
     }

     private LinkedList<Arc> mapAndSolveConflicts(){

        LinkedList<Arc> arcList = getAllPlaceToTransitionArcs();
        LinkedList<String> placesList = getAllDistinctPlaces(arcList);
        int allArcsWeight = 0;
        LinkedList<Arc> unselectedArcs = new LinkedList<>();

        for (String place : placesList) {
            //para cada lugar específico, procuro na lista de arcos possíveis os conflitantes
            LinkedList<Arc> conflictingArcs = new LinkedList<>();

            for (Arc a : arcList) {
                if(a.getOrigin().equalsIgnoreCase(place)) {
                    conflictingArcs.add(a);
                    //arcList.remove(a); //removo o arco da lista de verificação para não adicionar de novo
                    allArcsWeight = allArcsWeight + a.getWeight();
                }
            }
            //verifico se o peso dos arcos é maior a quantidade de marcas, se sim, faz o sorteio
            if(cycle.getPlaces().get(place) < allArcsWeight){
                unselectedArcs.addAll(raffle(conflictingArcs));
            } else {
                break;
            }
        }
        return unselectedArcs;
     }

     private LinkedList<Arc> raffle(LinkedList<Arc> conflictingArcs){
        int numberOfPlaces = cycle.getPlaces().size();
        int r;
        LinkedList<Arc> unselectedArcs = new LinkedList<>();

        do {
            r = (int) (Math.random() * conflictingArcs.size());
            numberOfPlaces = numberOfPlaces - conflictingArcs.get(r).getWeight();
            conflictingArcs.remove(r);

            for(int i=0; i<conflictingArcs.size(); i++){
                if (conflictingArcs.get(i).getWeight() > numberOfPlaces) {
                    unselectedArcs.add(conflictingArcs.remove(i));
                }
            }

        } while(numberOfPlaces > 0 && !conflictingArcs.isEmpty());

        return unselectedArcs;
     }

     private LinkedList<String> getAllDistinctPlaces(LinkedList<Arc> arcList){

        LinkedList<String> placesList = new LinkedList<>();

        for (Arc a: arcList) {
            String place = a.getOrigin();
            if(!placesList.contains(place)){
                placesList.add(place);
            }
        }
        return placesList;
     }

     private LinkedList<Arc> getAllPlaceToTransitionArcs(){

        LinkedList<Arc> arcLinkedList = new LinkedList<>();
        for (Arc a: cycle.getArcs()) {
            if(a.getDestination().startsWith("T") && a.getOrigin().startsWith("L")){
                //procuro todos os arcos que tem como destino transações e verifico se a origem é um lugar
                for (Transition t: cycle.getTransitions()) {
                    if(t.getName().equalsIgnoreCase(a.getDestination()) && t.isEnabled()) {
                        //verifico se a transação mapeada como destino naquele arco está habilitada
                        //se sim, adiciono ela na lista de arcos possíveis
                        arcLinkedList.add(a);
                        break;
                    }
                }
            }
        }

        return arcLinkedList;
     }

}
