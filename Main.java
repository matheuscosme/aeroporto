public class Main {
    public static void main(String[] args) {
        Aeroporto aeroporto = new Aeroporto();
        aeroporto.adicionarNovosAvioesFila();
        aeroporto.verFilas();
        System.out.println(aeroporto.checarEmergencia());
    }
}
