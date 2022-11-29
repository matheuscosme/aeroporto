public class Main {
    public static void main(String[] args) throws ErroMuitosAvioesEmergencia{
        Aeroporto aeroporto = new Aeroporto();
        aeroporto.adicionarNovosAvioesFila();
        aeroporto.verFilas();
        aeroporto.verPista();
        System.out.println("Emergencia: "+ aeroporto.checarEmergencia());
        aeroporto.pousoEmergencia();
        aeroporto.verFilas();
        aeroporto.verPista();
        aeroporto.pouso();
        aeroporto.verFilas();
        aeroporto.verPista();
    }
}
