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
                 "AVISO: Este programa apresenta validações afim de garantir que uma rede válida seja executada." +
                 "No entanto, não são tratados todos os casos de exceção para uma rede inválida.\n" +
                 " Opções de execução: \n"+
                 "   1. Importar de arquivo\n"+
                 "   2. Rede interativa\n"+
                 "   3. Ajuda\n"+
                 "   4. Sair\n"+
                 "----------------------------------------------\n"),
    INVALID_OPTION_ERROR("A opção selecionada não é válida. Por favor selecione 1, 2, 3 ou 4"),
    HELP_OPTION("Digite 0 para um exemplo de arquivo, ou qualquer outro número para continuar"),
    DOWNLOAD_OPTION("Digite 0 para fazer o download do arquivo, ou outro número para sair"),
    DOWNLOAD_COMPLETED("Download Completo!"),
    PLACES_INPUT("Insira a linha de lugares: \n"),
    ARCS_INPUT("Insira a linha de arcos: \n"),
    TRANSITIONS_INPUT("Insira a linha de transições: \n"),
    INPUT_MESSAGE("Iniciando o processamento da rede ... "),
    ERROR_PLACES_INPUT("Linha de lugares inválida. Os lugares devem iniciar com a letra L e a linha deve estar no seguinte formato: L1 2,L2 4 \n"),
    ERROR_ARCS_INPUT("Linha de arcos inválida. A linha de arcos deve estar no seguinte formato: A1 L1 T1 2,A2 T1 L2 3 \n"),
    ERROR_TRANSITIONS_INPUT("Linha de transição inválida. As transições devem iniciar com a letra T a linha estar no seguinte formato: T1, T2, T3 \n"),
    FILE_PATH("Insira o caminho do arquivo \n");

    private final String message;

    CustomMessages(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
