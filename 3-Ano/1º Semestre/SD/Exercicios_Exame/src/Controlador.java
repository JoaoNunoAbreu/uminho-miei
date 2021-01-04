interface Controlador {
    void requisita_viagem(int origem, int destino) throws InterruptedException;
    void espera(int destino) throws InterruptedException;
}