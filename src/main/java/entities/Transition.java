package entities;

import java.util.Hashtable;
import java.util.LinkedList;

public class Transition {

    private final String          name;
    private final LinkedList<Arc> inArcs;
    private final LinkedList<Arc> outArcs;
    private boolean         isEnabled;
    private static final String   outArc = "out";
    private static final String   inArc  = "in";

    public Transition(String name, LinkedList<Arc> inArcs, LinkedList<Arc> outArcs, Hashtable<String, Integer> places) {
        this.name    = name;
        this.inArcs  = inArcs;
        this.outArcs = outArcs;
        setEnabled(places);
    }

    public void setEnabled(Hashtable<String, Integer> places) {

        int count = 0;
        boolean statusChange = false;

        while (count < inArcs.size() && !statusChange) {
            if ((inArcs.get(count).getWeight() > places.get(inArcs.get(count).getOrigin()) && isEnabled) ||
                    (inArcs.get(count).getWeight() <= places.get(inArcs.get(count).getOrigin()) && !isEnabled)) {
                statusChange = true;
                isEnabled = !isEnabled;
            }
            count++;
        }

    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public String getName() {
        return name;
    }

    public Hashtable<String, Integer> execute(Hashtable<String, Integer> places){

        if(isEnabled){
            //noinspection ConstantConditions
            places = updatePlaces(inArcs, places, inArc);
            //noinspection ConstantConditions
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

    public LinkedList<Arc> getInArcs() {
        return inArcs;
    }

}
