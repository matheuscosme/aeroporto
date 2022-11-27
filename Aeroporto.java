import java.util.Random;

public class Aeroporto {
    Pista pista1;
    Pista pista2;
    Pista pista3; // pista decolagem
    Fila filaAterrissagem1;
    Fila filaAterrissagem2;
    Fila filaAterrissagem3;
    Fila filaAterrissagem4;
    Fila filaDecolagem1;
    int idAviaoImpares = 1;
    int idAviaoPares = 0;

    public Aeroporto() {
        this.pista1 = new Pista();
        this.pista2 = new Pista();
        this.pista3 = new Pista();
        this.filaAterrissagem1 = new Fila(pista1);
        this.filaAterrissagem2 = new Fila(pista1);
        this.filaAterrissagem3 = new Fila(pista2);
        this.filaAterrissagem4 = new Fila(pista2);
        this.filaDecolagem1 = new Fila(pista3);
    }

    public void verFilas() {
        System.out.println("***********");
        System.out.println("Fila Aterrissagem 1: ");
        filaAterrissagem1.printFila();
        System.out.println("Fila Aterrissagem 2: ");
        filaAterrissagem2.printFila();
        System.out.println("Fila Aterrissagem 3: ");
        filaAterrissagem3.printFila();
        System.out.println("Fila Aterrissagem 4: ");
        filaAterrissagem4.printFila();
        System.out.println("Fila Aterrissagem 5: ");
        filaDecolagem1.printFila();
    }

    public void diminuirCombustivelAumentarTempoEspera() {
        filaAterrissagem1.passarTempo();
        filaAterrissagem2.passarTempo();
        filaAterrissagem3.passarTempo();
        filaAterrissagem4.passarTempo();
        filaDecolagem1.passarTempo();
    }

    public void adicionarNovosAvioesFila() {
        // fila aterrissagem
        Random random = new Random();
        int quantidadeAvioesAterrissar = random.nextInt(2) + 1;
        for (int i = 0; i < quantidadeAvioesAterrissar; i++) {
            int combustivel = random.nextInt(19) + 1;
            Aviao aviao = new Aviao(idAviaoPares, combustivel);
            this.idAviaoPares = this.idAviaoPares + 2;
            // conferir a quantidade de avião nas pistas
            int quantidadeAvioesPista1 = this.filaAterrissagem1.quantidadeAvioes
                    + this.filaAterrissagem2.quantidadeAvioes;
            int quantidadeAvioesPista2 = this.filaAterrissagem3.quantidadeAvioes
                    + this.filaAterrissagem4.quantidadeAvioes;

            if (quantidadeAvioesPista2 >= quantidadeAvioesPista1) {
                if (this.filaAterrissagem2.quantidadeAvioes >= this.filaAterrissagem1.quantidadeAvioes) {
                    this.filaAterrissagem1.adicionarFinal(aviao);
                } else {
                    this.filaAterrissagem2.adicionarFinal(aviao);
                }
            } else {
                if (this.filaAterrissagem4.quantidadeAvioes >= this.filaAterrissagem3.quantidadeAvioes) {
                    this.filaAterrissagem3.adicionarFinal(aviao);
                } else {
                    this.filaAterrissagem4.adicionarFinal(aviao);
                }
            }
        }
        // fila decolagem
        int quantidadeAvioesDecolar = random.nextInt(2) + 1;
        for (int i = 0; i < quantidadeAvioesDecolar; i++) {
            Aviao aviao = new Aviao(idAviaoImpares, 20);
            this.idAviaoImpares = this.idAviaoImpares + 2;
            this.filaDecolagem1.adicionarFinal(aviao);
        }
    }

    public int checarEmergencia() {
        int quantidadeEmergencia = 0;
        quantidadeEmergencia += this.filaAterrissagem1.verificarTemAviaoComPoucoCombustivel();
        quantidadeEmergencia += this.filaAterrissagem2.verificarTemAviaoComPoucoCombustivel();
        quantidadeEmergencia += this.filaAterrissagem3.verificarTemAviaoComPoucoCombustivel();
        quantidadeEmergencia += this.filaAterrissagem4.verificarTemAviaoComPoucoCombustivel();
        return quantidadeEmergencia;
    }

    public void pousoEmergencia() {

    }

    public void pouso() {

    }

    public void decolagem() {

    }

    public void passarTempo() {

    }
}
