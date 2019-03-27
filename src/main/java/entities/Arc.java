package entities;

import java.security.InvalidParameterException;

public class Arc {

    private String origin;
    private String destination;
    private int    weight = 1;

    public Arc(String origin, String destination, int weight) throws InvalidParameterException{

        if(arePrefixesValid(origin, destination)){
            throw new InvalidParameterException("Os parâmetros de origem e destino do arco são inválidos. " +
                                                "Os mesmos devem obedecer a seguinte regra: " +
                                                "Ou a origem é um local e o destino é uma transição, " +
                                                "ou a origem é uma transição e o destino é um local. " +
                                                "Estes são os parâmetros passados: \n Origem: " + origin +
                                                "\n Destino: " + destination);
        }
        if(weight > 0){
            this.weight = weight;
        }
        this.origin      = origin;
        this.destination = destination;
    }

    private boolean arePrefixesValid(String origin, String destination){
        char originPrefix = origin.charAt(0);
        char destinationPrefix = destination.charAt(0);

        return (originPrefix == 'L' && destinationPrefix == 'T') || (originPrefix == 'T' && destinationPrefix == 'L');
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
