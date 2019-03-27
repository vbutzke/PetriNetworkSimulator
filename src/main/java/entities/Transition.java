package entities;

import java.util.Hashtable;
import java.util.LinkedList;

public class Transition {

    private String          name;
    private LinkedList<Arc> inArcs;
    private LinkedList<Arc> outArcs;
    private boolean         isEnabled;
    private static String   outArc = "out";
    private static String   inArc  = "in";

    public Transition(String name, LinkedList<Arc> inArcs, LinkedList<Arc> outArcs, Hashtable<String, Integer> places) {
        this.name    = name;
        this.inArcs  = inArcs;
        this.outArcs = outArcs;
        setEnabled(places);
    }

    private void setEnabled(Hashtable<String, Integer> places) {

        int count = 0;
        boolean statusChange = false;

        while(count < inArcs.size() || statusChange == false){
            if((inArcs.get(count).getWeight() >  places.get(inArcs.get(count).getOrigin()) && isEnabled) ||
               (inArcs.get(count).getWeight() <= places.get(inArcs.get(count).getOrigin()) && !isEnabled)){
                statusChange = true;
                isEnabled = !isEnabled;
            }
            count++;
        }
    }

    public Hashtable<String, Integer> execute(Hashtable<String, Integer> places){

        if(isEnabled){
            places = updatePlaces(inArcs, places, inArc);
            places = updatePlaces(outArcs, places, outArc);
        }

        return places;
    }

    private Hashtable<String, Integer> updatePlaces(LinkedList<Arc> arcs, Hashtable<String, Integer> places, String arcType){
        int count = 0;
        Arc arc;
        String key;
        while(count < arcs.size()){
            arc = arcs.get(count);
            switch (arcType){
                case "in":
                    key = arc.getOrigin();
                    places.replace(key, places.get(key) - arc.getWeight());
                    break;
                case "out":
                    key = arc.getDestination();
                    places.replace(key, places.get(key) + arc.getWeight());
                    break;
            }

            count++;
        }
        return places;
    }

    //validação nos in arcs pra quando tem conflito por marca
    //algoritmo de sorteio
}
