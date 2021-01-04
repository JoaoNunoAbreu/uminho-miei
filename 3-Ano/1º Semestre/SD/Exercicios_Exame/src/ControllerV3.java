import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ControllerV3 implements Controlador {

    private int terminal = -1;
    private int ocupacao = 0;
    private int[] pdestino = {0,0,0,0,0};
    private int[] pespera = {0,0,0,0,0};
    private ReentrantLock l = new ReentrantLock();
    private Condition espera_entrar = l.newCondition();    // Condição de passageiro
    private Condition espera_sair = l.newCondition();      // Condição de passageiro
    private Condition passag_entrar = l.newCondition();    // Condição do autocarro
    private Condition passag_sair = l.newCondition();      // Condição do autocarro

    @Override
    public void requisita_viagem(int origem, int destino) throws InterruptedException {
        l.lock();
        pespera[origem]++;
        while(origem != terminal && ocupacao == 30){
            espera_entrar.await();
        }
        ocupacao++;
        pespera[origem]--;
        pdestino[destino]++;
        passag_entrar.signalAll(); // porque partimos a condição de espera do método parte()
        l.unlock();
    }

    @Override
    public void espera(int destino) throws InterruptedException {
        l.lock();
        while(destino != terminal){
            espera_sair.await();
        }
        ocupacao--;
        pdestino[destino]--;
        passag_sair.signalAll();
        l.unlock();
    }

    public void parte() throws InterruptedException {
        l.lock();
        terminal = -terminal;
        espera_sair.signalAll();
        while(pdestino[terminal] > 0){
            passag_sair.await();
        }
        while(!(ocupacao == 30 || (pespera[terminal] == 0 && ocupacao > 10))){
            passag_entrar.await();
        }
        espera_entrar.signalAll();
        if(terminal == 5) terminal = -1;
        else terminal = -1*(terminal +1);
        l.unlock();
    }
}
