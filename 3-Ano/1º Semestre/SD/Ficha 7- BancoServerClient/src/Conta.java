import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Conta{

    private int id;
    private double valor;
    private List<Movimento> movimentos;

    private ReentrantLock contaLock = new ReentrantLock();

    public Conta(int id, double valor){
        this.id = id;
        this.valor = valor;
        this.movimentos = new ArrayList<>();
    }

    public int getId(){return this.id;}

    public List<Movimento> getMovimentos(){
        return new ArrayList<Movimento>(this.movimentos);
    }

    public void addMovimento(int id_op, String descritivo, double saldo_final){
        this.movimentos.add(new Movimento(id_op,descritivo,saldo_final));
    }

    public void depositar(double valor){
        this.valor += valor;
    }

    public void levantar(double valor){
        this.valor -= valor;
    }

    public double consultar(){
        return this.valor;
    }

    public void lock(){
        this.contaLock.lock();
    }
    public void unlock(){
        this.contaLock.unlock();
    }
}
