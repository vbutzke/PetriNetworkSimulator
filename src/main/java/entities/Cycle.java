package entities;

import java.util.Hashtable;
import java.util.LinkedList;

@SuppressWarnings("StringConcatenationInLoop")
public class Cycle {

    private int step;
    private Hashtable<String, Integer> places = new Hashtable<>();
    private final LinkedList<Arc> arcs = new LinkedList<>();
    private final LinkedList<Transition> transitions = new LinkedList<>();
    private String output = "";

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

        for (String token : tokens) {
            LinkedList<Arc> inArcs = new LinkedList<>();
            LinkedList<Arc> outArcs = new LinkedList<>();
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

    public Hashtable<String, Integer> getPlaces() {
        return places;
    }

    public LinkedList<Arc> getArcs() {
        return arcs;
    }

    public LinkedList<Transition> getTransitions() {
        return transitions;
    }

    public void nextStep(){
        step++;
    }

    private void buildOutput(){
        output = " | " + step + " | ";

        for (String key : places.keySet()) {
            output = output + places.get(key) + " | ";
        }

        for (Transition transition : transitions) {
            output = output + transition.isEnabled() + " | ";
        }
    }

    public String getOutput(){
        buildOutput();
        return output;
    }

    public void setPlaces(Hashtable<String, Integer> places) {
        this.places = places;
    }

}
