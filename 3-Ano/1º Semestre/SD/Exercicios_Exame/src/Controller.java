import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Controller implements Controlador {

    private int terminal = -1;
    private int[] pdestino = {0,0,0,0,0};
    private ReentrantLock l = new ReentrantLock();
    private Condition espera_entrar = l.newCondition();    // Condição de passageiro
    private Condition espera_sair = l.newCondition();      // Condição de passageiro
    private Condition passag_sair = l.newCondition();      // Condição do autocarro

    @Override
    public void requisita_viagem(int origem, int destino) throws InterruptedException {
        l.lock();
        while(origem != terminal){
            espera_entrar.await();
        }
        pdestino[destino]++;
        l.unlock();
    }

    @Override
    public void espera(int destino) throws InterruptedException {
        l.lock();
        while(destino != terminal){
            espera_sair.await();
        }
        pdestino[destino]--;
        passag_sair.signalAll();
        l.unlock();
    }

    public void parte() throws InterruptedException {
        l.lock();
        while(pdestino[terminal] > 0){
            passag_sair.await();
        }
        if(terminal == 5) terminal = -1;
        else terminal = -1*(terminal +1);
        espera_entrar.signalAll();
        espera_sair.signalAll();
        l.unlock();
    }
}
