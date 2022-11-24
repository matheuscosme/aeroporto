public class Main {
    public static void main(String[] args) {
        int proximaFilaAterrisagem = 1;
        int filaPrioridadePouso = 1;
        int ultimoId = 0;
        int combustivel;
        int cont = 0;
        Pista pista1 = new Pista(); //Aterrissagem
        Pista pista2 = new Pista(); //Aterrissagem
        Pista pista3 = new Pista(); //Decolagem


        FilaAterrissagem filaAterrissagem1 = new FilaAterrissagem();
        FilaAterrissagem filaAterrissagem2 = new FilaAterrissagem();
        FilaAterrissagem filaAterrissagem3 = new FilaAterrissagem();
        FilaAterrissagem filaAterrissagem4 = new FilaAterrissagem();


        while(cont < 2){

            //Receber aviões para Aterrissar!
            int numAvioesAterrissar = 3;
            for (int i=0;i<numAvioesAterrissar;i++){
                combustivel = 15;
                Aviao aviao = new Aviao(ultimoId, combustivel);
                if (proximaFilaAterrisagem == 1){
                    filaAterrissagem1.adicionarFinal(aviao);
                    ultimoId = ultimoId +2;
                }
                if (proximaFilaAterrisagem == 2){
                    filaAterrissagem2.adicionarFinal(aviao);
                    ultimoId = ultimoId +2;
                }
                if (proximaFilaAterrisagem == 3){
                    filaAterrissagem3.adicionarFinal(aviao);
                    ultimoId = ultimoId +2;
                }
                if (proximaFilaAterrisagem == 4){
                    filaAterrissagem4.adicionarFinal(aviao);
                    ultimoId = ultimoId +2;
                }
                proximaFilaAterrisagem++;
                if (proximaFilaAterrisagem > 4){
                    proximaFilaAterrisagem = 1;
                }
            }

            System.out.println("Aviões entram nas filas: ");
            System.out.println("Fila 1: ");
            filaAterrissagem1.printFila();

            System.out.println("Fila 2: ");
            filaAterrissagem2.printFila();

            System.out.println("Fila 3: ");
            filaAterrissagem3.printFila();

            System.out.println("Fila 4: ");
            filaAterrissagem4.printFila();

            //Aterrissar Aviões
            if(pista1.pistaLiberada()){
                pista1.pouso();
                if(filaPrioridadePouso == 1){
                    filaAterrissagem1.removerInicio();
                }
                if(filaPrioridadePouso == 2){
                    filaAterrissagem2.removerInicio();
                }
                if(filaPrioridadePouso == 3){
                    filaAterrissagem3.removerInicio();
                }
                if(filaPrioridadePouso == 4){
                    filaAterrissagem4.removerInicio();
                    filaPrioridadePouso = 0;
                }
                filaPrioridadePouso++;
            }
            if(pista2.pistaLiberada()){
                pista2.pouso();
                if(filaPrioridadePouso == 1){
                    filaAterrissagem1.removerInicio();
                }
                if(filaPrioridadePouso == 2){
                    filaAterrissagem2.removerInicio();
                }
                if(filaPrioridadePouso == 3){
                    filaAterrissagem3.removerInicio();
                }
                if(filaPrioridadePouso == 4){
                    filaAterrissagem4.removerInicio();
                    filaPrioridadePouso = 0;
                }
                filaPrioridadePouso++;
            }

            pista1.liberarPista();
            pista2.liberarPista();
            pista3.liberarPista();

            filaAterrissagem1.passarTempo();
            filaAterrissagem2.passarTempo();
            filaAterrissagem3.passarTempo();
            filaAterrissagem4.passarTempo();

            System.out.println("Após o pouso: ");
            System.out.println("Fila 1: ");
            filaAterrissagem1.printFila();

            System.out.println("Fila 2: ");
            filaAterrissagem2.printFila();

            System.out.println("Fila 3: ");
            filaAterrissagem3.printFila();

            System.out.println("Fila 4: ");
            filaAterrissagem4.printFila();
            
            
            System.out.println("---------- FIM DO " + (cont+1) + "º ciclo ----------- \n\n\n");
            cont++;

        }
    }
}
