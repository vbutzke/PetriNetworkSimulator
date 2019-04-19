package entities;

import java.util.Hashtable;
import java.util.LinkedList;

public class Cycle {

    private int step;
    private Hashtable<String, Integer> places = new Hashtable<>();
    private LinkedList<Arc> arcs = new LinkedList<>();
    private LinkedList<Transition> transitions = new LinkedList<>();

    public Cycle(String placesLine, String transitionsLine, String arcsLine) {
        step = 0;
        fillPlaces(placesLine);
        fillArcs(arcsLine);
        fillTransitions(transitionsLine);
    }

    private void fillPlaces(String line){
        String [] tokens = line.split(",");

        for (String token : tokens) {
            String[] placeMarkPair = token.split(" ");
            places.put(placeMarkPair[0], Integer.parseInt(placeMarkPair[1]));
        }
    }

    private void fillArcs(String line){
        String [] tokens = line.split(",");

        for (String token : tokens) { //AN L1 T1 / AN T1 L1}
            String[]alt = token.split(" ");
            Arc arc = new Arc(alt[1], alt[2], Integer.parseInt(alt[3]));
            arcs.add(arc);
        }
    }

    private void fillTransitions(String line){
        String [] tokens = line.split(",");
        LinkedList<Arc> inArcs = new LinkedList<>();
        LinkedList<Arc> outArcs = new LinkedList<>();

        for (String token : tokens) {
            for (Arc arc : arcs) {
                if(arc.getOrigin().equalsIgnoreCase(token)){
                    outArcs.add(arc);
                } else if(arc.getDestination().equalsIgnoreCase(token)) {
                    inArcs.add(arc);
                }
            }
            transitions.add(new Transition(token, inArcs, outArcs, places));
        }
    }

    public void execute(){
        //TODO a validação de marca e sorteio tem que ser aqui porque as transações são diferentes in arcs
        // tenho que atualizar os lugares  também e acho que as transações também



        for (int i=0; i<transitions.size(); i++){
            places = transitions.get(i).execute(places);
            transitions.get(i).setEnabled(places);
            if(transitions.get(i).isEnabled()){
                i--;
            }
        }

        nextStep();
    }

    public int getStep() {
        return step;
    }

    public Hashtable<String, Integer> getPlaces() {
        return places;
    }

    public LinkedList<Arc> getArcs() {
        return arcs;
    }

    public LinkedList<Transition> getTransitions() {
        return transitions;
    }

    private void nextStep(){
        step++;
    }

    public void interactiveInput(String placesLine, String transitionsLine, String arcsLine){
        fillPlaces(placesLine);
        fillArcs(arcsLine);
        fillTransitions(transitionsLine);
    }

    public void printCycle(){
        System.out.println("Step: "+step);
        System.out.println("Places: ");
        for (String key: places.keySet()) {
            System.out.println(key+" - "+places.get(key));
        }
        System.out.println();
        System.out.println("Arcs: ");
        for (Arc arc: arcs) {
            arc.printArc();
        }
        System.out.println();
        System.out.println("Transitions: ");
        for (Transition t:transitions) {
            t.printTransition();
        }
        System.out.println();
    }

    public void printPlaces(){
        System.out.println("Places: ");
        for (String key: places.keySet()) {
            System.out.println(key+" - "+places.get(key));
        }
    }

    private void mapAndSolveConflicts(){

        LinkedList<Arc> arcList = getAllPlaceToTransitionArcs();
        LinkedList<String> placesList = getAllDistinctPlaces(arcList);
        int allArcsWeight = 0;

        for (String place : placesList) {
            //para cada lugar específico, procuro na lista de arcos possíveis os conflitantes
            LinkedList<Arc> conflictingArcs = new LinkedList<>();

            for (Arc a : arcList) {
                if(a.getOrigin().equalsIgnoreCase(place)) {
                    conflictingArcs.add(a);
                    arcList.remove(a); //removo o arco da lista de verificação para não adicionar de novo
                    allArcsWeight = allArcsWeight + a.getWeight();
                }
            }
            //verifico se o peso dos arcos é menor a quantidade de marcas, se sim, faz o sorteio
            if(places.get(place) < allArcsWeight){
                //faz sorteio
            } //se não, não faz sorteio
            // o que retornar e como ajustar o execute?

            //conflictingArcs
        }



        //nesse caso eu ordeno e comparo os lugares de origem, se for o mesmo, tem que fazer um sorteio, mas só se a quantidade de marcas for menor que todas os pesos dos arcos
        //preciso a origem, a transação final, o peso do arco e a quantidade de marcas

    }

    private void raffle(LinkedList<Arc> conflictingArcs){




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
        for (Arc a: arcs) {
            if(a.getDestination().startsWith("T") && a.getOrigin().startsWith("L")){
                //procuro todos os arcos que tem como destino transações e verifico se a origem é um lugar
                for (Transition t: transitions) {
                    if(t.getName().equalsIgnoreCase(a.getDestination()) && t.isEnabled())
                        //verifico se a transação mapeada como destino naquele arco está habilitada
                        //se sim, adiciono ela na lista de arcos possíveis
                        arcLinkedList.add(a);
                        break;
                }
            }
        }

        return arcLinkedList;
    }


    //TODO run code inspector
    //TODO remove unused code

}
