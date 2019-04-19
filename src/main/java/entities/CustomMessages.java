package entities;

public enum CustomMessages {

    ARC_EXCEPTION("Os parâmetros de origem e destino do arco são inválidos. " +
                  "Os mesmos devem obedecer as seguintes regras: " +
                  "Ou a origem é um local e o destino é uma transição,\n" +
                  "Ou a origem é uma transição e o destino é um local \n" +
                  "Ou a origem é uma transição e o destino também é uma transição \n"),

    FILE_FORMAT("-------- Input Interativo -------- \n" +
                "   O input interativo deve conter 3 linhas.\n" +
                "Na primeira linha, devem ser descritos os lugares\n" +
                "Na segunda linha, devem ser descritos os arcos \n" +
                "Na terceira linha, devem ser descritos as transições \n" +
                "\n"+
                "-------- Regras -------- \n" +
                "Lugares: [Nome do lugar] [Número de marcas], \n" +
                "   Exemplo: L1 2,L2 4 \n"+
                "   Deve iniciar com a letra L\n"+
                "Arcos: [Nome do arco] [Origem] [Destino] [Peso] \n" +
                "   Exemplo: A1 L1 T1 2,A2 T1 L2 3"+
                "   Um arco pode ser traçado de um lugar a uma transição, de uma transição a um lugar ou de uma transição a outra transição"+
                "Transições: [Nome da transição] \n" +
                "   Exemplo: T1, T2, T3" +
                "   Deve iniciar com a letra T"
    ),

    FILE_EXAMPLE("--- Exemplo de Arquivo --- \n"+
                 "L1 3,L2 0 \n" +
                 "A1 L1 T1 2,A2 T1 L2 3\n" +
                 "T1 \n"+
                 "-------------------------- \n"),

    INIT_PROGRAM("--------------- Rede de Petri --------------- \n"+
                 " Opções de execução: \n"+
                 "   1. Importar de arquivo\n"+
                 "   2. Rede interativa\n"+
                 "   3. Ajuda\n"+
                 "----------------------------------------------\n");

    private String message;

    CustomMessages(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
