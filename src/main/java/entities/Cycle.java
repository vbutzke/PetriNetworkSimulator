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
            Arc arc = new Arc(alt[1], alt[2], Integer.parseInt(alt[1].substring(1)));
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
    //TODO print function for all classes
    //TODO run code inspector
    //TODO remove unused code

}
