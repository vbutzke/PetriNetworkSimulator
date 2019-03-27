package entities;

import java.util.Hashtable;
import java.util.LinkedList;

public class Cycle {

    private int step;
    private Hashtable<String, Integer> places;
    private LinkedList<Transition> transitions;
    private String output;

    public Cycle(Hashtable<String, Integer> places, LinkedList<Transition> transitions) {
        step = 0;
        this.places      = places;
        this.transitions = transitions;
    }

    public String execute(){

        step++;
        for (int i=0; i<transitions.size(); i++){
            transitions.get(i).execute(places);
        }
        return output;
    }
}
