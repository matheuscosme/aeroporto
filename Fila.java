public class Fila {
    public No inicio_fila;
    public int quantidadeAvioes;
    public Pista pista;

    public Fila(Pista pista) {
        this.quantidadeAvioes = 0;
        this.inicio_fila = null;
        this.pista = pista;
    }

    public boolean filaVazia() {
        if (this.inicio_fila == null) {
            return true;
        } else {
            return false;
        }
    }

    public void adicionarFinal(Aviao aviao) {
        No novo = new No(aviao);
        this.quantidadeAvioes++;
        if (filaVazia()) {
            this.inicio_fila = novo;
        } else {
            No auxiliar = this.inicio_fila;
            while (auxiliar.proximoNo != null) {
                auxiliar = auxiliar.proximoNo;
            }
            auxiliar.proximoNo = novo;
        }
    }

    public No removerInicio() {
        No removido = null;
        if (filaVazia()) {
            System.out.println(" ");
            //fila vazia
        } else {
            removido = this.inicio_fila;
            this.inicio_fila = inicio_fila.proximoNo;
            this.quantidadeAvioes--;
        }
        return removido;
    }

    public void passarTempo() {
        No auxiliar = this.inicio_fila;
        while (auxiliar != null) {
            auxiliar.aviao.combustivel = auxiliar.aviao.combustivel - 1;
            auxiliar.aviao.tempoEspera = auxiliar.aviao.tempoEspera + 1;
            auxiliar = auxiliar.proximoNo;
        }
    }

    public void printFila() {
        No auxiliar = this.inicio_fila;
        System.out.println("Quantidade Aviões na fila: " + this.quantidadeAvioes);
        while (auxiliar != null) {
            System.out.println("Avião Id: " + auxiliar.aviao.id + " Combustivel: " + auxiliar.aviao.combustivel
                    + " Tempo de Espera: " + auxiliar.aviao.tempoEspera);
            auxiliar = auxiliar.proximoNo;
        }
        System.out.println();
    }

    public int verificarTemAviaoComPoucoCombustivel() {
        int quantidadeAvioesPoucoCombustivel = 0;
        No auxiliar = this.inicio_fila;
        while (auxiliar != null) {
            if (auxiliar.aviao.poucoCombustivelAviao()) {
                quantidadeAvioesPoucoCombustivel++;
            }
            auxiliar = auxiliar.proximoNo;
        }
        return quantidadeAvioesPoucoCombustivel;
    }
    
    //usado pra colocar o avião na frente da fila
    public void adicionarInicio(Aviao aviao) {
        this.adicionarFinal(aviao);
        for (int i = 0; i < this.quantidadeAvioes - 1; i++) {
            No apoio = this.removerInicio();
            this.adicionarFinal(apoio.aviao);
        }
    }

    public void colocarAviaoFrente() {
        if (this.verificarTemAviaoComPoucoCombustivel() > 0) {
            No auxiliar = this.inicio_fila;
            No aviaoPoucaGasolina = this.inicio_fila;
            for (int i = 0; i <= quantidadeAvioes; i++) {
                No aux = this.removerInicio();
                if (!aux.aviao.poucoCombustivelAviao()) {
                    this.adicionarFinal(aux.aviao);
                } else {
                    aviaoPoucaGasolina = aux;
                    // interacao++;
                }
                auxiliar = auxiliar.proximoNo;
            }
            this.adicionarInicio(aviaoPoucaGasolina.aviao);
        }
    }
}
