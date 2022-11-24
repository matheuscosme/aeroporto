public class FilaAterrissagem {
  public No inicio_fila;
  
  public FilaAterrissagem(){
      this.inicio_fila = null;
  }

  public boolean filaVazia() {
    if(this.inicio_fila == null) {
      return true;
    } else {
      return false;
    }
  }

  public void adicionarFinal(Aviao aviao) {
    No novo = new No(aviao);
    if(filaVazia()){
      this.inicio_fila = novo;
    } else {
      No auxiliar = this.inicio_fila;
      while (auxiliar.proximoNo != null) {
        auxiliar = auxiliar.proximoNo;
      }
      auxiliar.proximoNo = novo;
    }
  }

  public No removerInicio(){
      No removido = null;
      if (filaVazia()) {
          System.out.println("Fila vazia");
      } else {
          removido = this.inicio_fila;
          this.inicio_fila = inicio_fila.proximoNo;
      }
      return removido;
  }

  public void passarTempo(){
    No auxiliar = this.inicio_fila;
    while (auxiliar != null) {
        auxiliar.aviao.combustivel = auxiliar.aviao.combustivel - 1;
        auxiliar = auxiliar.proximoNo;
    }
  }

  public void printFila(){
      No auxiliar = this.inicio_fila;
      while (auxiliar != null) {
          System.out.println("Avião Id: " + auxiliar.aviao.id + " Combustivel: " + auxiliar.aviao.combustivel);
          auxiliar = auxiliar.proximoNo;
      }
      System.out.println();
  }

}