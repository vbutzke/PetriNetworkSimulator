package entities;

import java.security.InvalidParameterException;

public class Arc {

    private String origin;
    private String destination;
    private int    weight = 1;

    public Arc(String origin, String destination, int weight) throws InvalidParameterException{

        if(!arePrefixesValid(origin, destination)){
            throw new InvalidParameterException("Os parâmetros de origem e destino do arco são inválidos. " +
                                                "Os mesmos devem obedecer a seguinte regra: " +
                                                "Ou a origem é um local e o destino é uma transição, " +
                                                "ou a origem é uma transição e o destino é um local. " +
                                                "ou a origem é uma transição e o destino também é uma transição" +
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

        char o = origin.charAt(0);
        char d = destination.charAt(0);
        String oSuf = origin.substring(1);
        String dSuf = destination.substring(1);

        if((o!='L' && o!= 'T') ||
           (d!='L' && d!= 'T') || //quando não é L ou T
           (o==d && o=='L')    || // quando os dois são L
           !isSuffixAnumber(oSuf) ||
           !isSuffixAnumber(dSuf) ||
           (o==d && oSuf.equalsIgnoreCase(dSuf))  // quando os dois são T e o resto é igual
        )
            return false;

        return true;
    }

    private boolean isSuffixAnumber(String suffix){
        try{
            Integer.parseInt(suffix);
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    public void printArc(){
        System.out.println("Arc - Origin: "+origin+" - Destination: "+destination+ " - Weight: " + weight);
    }

}
