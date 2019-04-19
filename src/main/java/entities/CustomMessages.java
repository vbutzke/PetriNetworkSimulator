package entities;

public enum CustomMessages {

    ARC_EXCEPTION("Os parâmetros de origem e destino do arco são inválidos. " +
                  "Os mesmos devem obedecer as seguintes regras: " +
                  "Ou a origem é um local e o destino é uma transição,\n" +
                  "Ou a origem é uma transição e o destino é um local \n" +
                  "Ou a origem é uma transição e o destino também é uma transição \n"),

    FILE_FORMAT("Lugares: [Nome do lugar] [Número de marcas], \n" +
                "   Exemplo: L1 2,L2 4 \n"+
                "   Deve iniciar com a letra L\n"+
                "Arcos: [Nome do arco] [Origem] [Destino] [Peso] \n" +
                "   Exemplo: A1 L1 T1 2,A2 T1 L2 3"+
                "   Um arco pode ser traçado de um lugar a uma transição, de uma transição a um lugar ou de uma transição a outra transição"+
                "Transições: [Nome da transição] \n" +
                "   Exemplo: T1, T2, T3" +
                "   Deve iniciar com a letra T"
    );

    private String message;

    CustomMessages(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
