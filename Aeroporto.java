import java.util.ArrayList;
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
    ArrayList<No> avioesAterrissagem;
    ArrayList<No> avioesDecolagem;

    public Aeroporto() {
        this.pista1 = new Pista();
        this.pista2 = new Pista();
        this.pista3 = new Pista();
        this.filaAterrissagem1 = new Fila(pista1);
        this.filaAterrissagem2 = new Fila(pista1);
        this.filaAterrissagem3 = new Fila(pista2);
        this.filaAterrissagem4 = new Fila(pista2);
        this.filaDecolagem1 = new Fila(pista3);
        this.avioesAterrissagem = new ArrayList<No>();
        this.avioesDecolagem = new ArrayList<No>();
    }

    public void verFilas() {
        System.out.println("***");
        System.out.println("Fila Aterrissagem 1 (Pista 1): ");
        filaAterrissagem1.printFila();
        System.out.println("Fila Aterrissagem 2 (Pista 1): ");
        filaAterrissagem2.printFila();
        System.out.println("Fila Aterrissagem 3 (Pista 2): ");
        filaAterrissagem3.printFila();
        System.out.println("Fila Aterrissagem 4 (Pista 2): ");
        filaAterrissagem4.printFila();
        System.out.println("Fila Decolagem 1 (Pista 3): ");
        filaDecolagem1.printFila();
        System.out.println("***");
    }

    public void verPista() {
        System.out.println("**");
        System.out.println("Pista 1 tá livre: " + this.pista1.getLivre());
        System.out.println("Pista 2 tá livre: " + this.pista2.getLivre());
        System.out.println("Pista 3 tá livre: " + this.pista3.getLivre());
        System.out.println("**");
    }

    public void diminuirCombustivelAumentarTempoEspera() {
        filaAterrissagem1.passarTempo();
        filaAterrissagem2.passarTempo();
        filaAterrissagem3.passarTempo();
        filaAterrissagem4.passarTempo();
        filaDecolagem1.passarTempo();
    }

    public void adicionarNovosAvioesFila() {
        // adicionar na fila aterrissagem
        Random random = new Random();
        int quantidadeAvioesAterrissar = random.nextInt(2) + 1;
        System.out.println("Foram adicionados " + quantidadeAvioesAterrissar + " aviões nas filas de aterrissagem");
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
        // adicionar fila decolagem
        int quantidadeAvioesDecolar = random.nextInt(2) + 1;
        System.out.println("Foram adicionados " + quantidadeAvioesDecolar + " aviões na fila de decolagem");
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

    public void pousoEmergencia() throws ErroMuitosAvioesEmergencia {
        int quantidadeEmergencia = this.checarEmergencia();
        if (quantidadeEmergencia > 3){
            throw new ErroMuitosAvioesEmergencia();
        } else if (quantidadeEmergencia == 1){
            //verificar em que fila o avião tá
            //colocar ele na frente
            //retirar
            if (this.filaAterrissagem1.verificarTemAviaoComPoucoCombustivel() > 0){
                this.filaAterrissagem1.colocarAviaoFrente();
                this.avioesAterrissagem.add(this.filaAterrissagem1.removerInicio());
            } else if (this.filaAterrissagem2.verificarTemAviaoComPoucoCombustivel() > 0){
                this.filaAterrissagem2.colocarAviaoFrente();
                this.avioesAterrissagem.add(this.filaAterrissagem2.removerInicio());
            } else if (this.filaAterrissagem3.verificarTemAviaoComPoucoCombustivel() > 0){
                this.filaAterrissagem3.colocarAviaoFrente();
                this.avioesAterrissagem.add(this.filaAterrissagem3.removerInicio());
            } else if (this.filaAterrissagem4.verificarTemAviaoComPoucoCombustivel() > 0){
                this.filaAterrissagem4.colocarAviaoFrente();
                this.avioesAterrissagem.add(this.filaAterrissagem4.removerInicio());
            }
            //ocupar a pista 3 com esse avião descendo
            this.pista3.pouso();
        } else if (quantidadeEmergencia == 2){
            //verificar em que fila o avião tá
            //colocar ele na frente
            //retirar
            //verificar que pista ocupar
            int pistaOcupar = 0;
            if (this.filaAterrissagem1.verificarTemAviaoComPoucoCombustivel() > 0){
                this.filaAterrissagem1.colocarAviaoFrente();
                this.avioesAterrissagem.add(this.filaAterrissagem1.removerInicio());
            }
            if (this.filaAterrissagem2.verificarTemAviaoComPoucoCombustivel() > 0){
                this.filaAterrissagem2.colocarAviaoFrente();
                this.avioesAterrissagem.add(this.filaAterrissagem2.removerInicio());
                pistaOcupar = 1;
            }
            if (this.filaAterrissagem3.verificarTemAviaoComPoucoCombustivel() > 0){
                this.filaAterrissagem3.colocarAviaoFrente();
                this.avioesAterrissagem.add(this.filaAterrissagem3.removerInicio());
                if (pistaOcupar == 0){
                    pistaOcupar = 2;
                }
            }  
            if (this.filaAterrissagem4.verificarTemAviaoComPoucoCombustivel() > 0){
                this.filaAterrissagem4.colocarAviaoFrente();
                this.avioesAterrissagem.add(this.filaAterrissagem4.removerInicio());
                pistaOcupar = 2;
            }
            //ocupar a pista 3 com esse avião descendo
            this.pista3.pouso();
            //ocupar outra pista com o avião descendo
            if (pistaOcupar == 2){
                this.pista2.pouso();
            } else {
                this.pista1.pouso();
            }

        } else if (quantidadeEmergencia == 3){
            //verificar em que fila o avião tá
            //colocar ele na frente
            //retirar
            if (this.filaAterrissagem1.verificarTemAviaoComPoucoCombustivel() > 0){
                this.filaAterrissagem1.colocarAviaoFrente();
                this.avioesAterrissagem.add(this.filaAterrissagem1.removerInicio());
            }
            if (this.filaAterrissagem2.verificarTemAviaoComPoucoCombustivel() > 0){
                this.filaAterrissagem2.colocarAviaoFrente();
                this.avioesAterrissagem.add(this.filaAterrissagem2.removerInicio());
            }
            if (this.filaAterrissagem3.verificarTemAviaoComPoucoCombustivel() > 0){
                this.filaAterrissagem3.colocarAviaoFrente();
                this.avioesAterrissagem.add(this.filaAterrissagem3.removerInicio());
            }  
            if (this.filaAterrissagem4.verificarTemAviaoComPoucoCombustivel() > 0){
                this.filaAterrissagem4.colocarAviaoFrente();
                this.avioesAterrissagem.add(this.filaAterrissagem4.removerInicio());
            }
            //ocupar todas as pistas 
            this.pista3.pouso();
            this.pista2.pouso();
            this.pista1.pouso();
        }
    }

    public void pouso() {
        //verificar se a pista está livre
        
        if (this.pista1.getLivre()){
            //ver qual fila dela tem mais aviões para retirar
            if (this.filaAterrissagem1.quantidadeAvioes >= this.filaAterrissagem2.quantidadeAvioes){
                this.avioesAterrissagem.add(this.filaAterrissagem1.removerInicio());
            } else {
                this.avioesAterrissagem.add(this.filaAterrissagem2.removerInicio());
            }
            //ocupar a pista
            this.pista1.pouso();
        } 

        if (this.pista2.getLivre()){
            if (this.filaAterrissagem3.quantidadeAvioes >= this.filaAterrissagem4.quantidadeAvioes){
                this.avioesAterrissagem.add(this.filaAterrissagem3.removerInicio());

            } else {
                this.avioesAterrissagem.add(this.filaAterrissagem4.removerInicio());
            }
            this.pista2.pouso();
        } 
    }

    public void decolagem() {
        //verificar se a pista está livre
        if (this.pista3.getLivre()){
            this.avioesDecolagem.add(this.filaDecolagem1.removerInicio());
            this.pista3.decolagem();
        }
    }

    public void printArraylistDecolagem(){
        System.out.println("Tamanho fila decolagem: " + this.avioesDecolagem.size());
        for (int i = 0; i < this.avioesDecolagem.size() ; i++){
            System.out.printf("%d %s",this.avioesDecolagem.get(i).aviao.id , " ");
        }
        System.out.println(" ");
    }

    public void tempoEsperaDecolagem(){
        if (this.avioesDecolagem.size() > 0){
            System.out.print("Tempo de espera médio da decolagem: ");
            System.out.println(this.avioesDecolagem.get(0).aviao.tempoEspera );
        } else {
            System.out.println("Tempo de espera médio da decolagem: - ");
        }
        this.avioesDecolagem.clear();
    }

    public void tempoEsperaAterrissagem(){
        if (this.avioesAterrissagem.size() > 0){
            System.out.print("Tempo de espera médio da aterrisagem: ");
            if (this.avioesAterrissagem.size() == 1){
                System.out.println(this.avioesAterrissagem.get(0).aviao.tempoEspera );
            } 
            //FALTA LÓGICA
            System.out.println();
        } else {
            System.out.println("Tempo de espera médio da aterrisagem: - ");
        }
        this.avioesAterrissagem.clear();
    }

    public void passarTempo()throws ErroMuitosAvioesEmergencia {
        System.out.println();
        System.out.println("**** Início Unidade de Tempo ****");
        this.diminuirCombustivelAumentarTempoEspera();
        this.adicionarNovosAvioesFila();
        System.out.println("Número de aviões que aterrissam sem reserva de combustível: " + this.checarEmergencia());
        this.pousoEmergencia();
        this.pouso();
        this.decolagem();
        this.pista1.liberarPista();
        this.pista2.liberarPista();
        this.pista3.liberarPista();
        System.out.println("Número de aviões que aterrissaram: " + this.avioesAterrissagem.size());
        System.out.println("Número de aviões que decolaram: " + this.avioesDecolagem.size());
        this.verFilas();
        this.tempoEsperaDecolagem();
        this.tempoEsperaAterrissagem();
        System.out.println("***** Fim Unidade de Tempo *****");
        System.out.println();

    }
}
