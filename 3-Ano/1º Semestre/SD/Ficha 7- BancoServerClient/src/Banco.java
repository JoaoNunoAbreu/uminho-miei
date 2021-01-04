import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Banco implements Bank{

    private Map<Integer,Conta> contas;
    private ReentrantLock lockBanco = new ReentrantLock();

    private int lastID = 0;

    public Banco(int numContas){
        this.contas = new HashMap<>(numContas);
    }

    public double consulta(int n) throws InvalidAccount{
        if(!this.contas.containsKey(n))
            throw new InvalidAccount("Conta Inválida!");
        else return this.contas.get(n).consultar();
    }

    public void credito(int n, double quantia) throws InvalidAccount{
        if(!this.contas.containsKey(n)){
            throw new InvalidAccount("Conta Inválida!");
        }
        else this.contas.get(n).depositar(quantia);
    }

    public void debito(int n, double quantia)  throws InvalidAccount, NotEnoughFunds{
        if(!this.contas.containsKey(n)){
            throw new InvalidAccount("Conta Inválida!");
        }
        else if(this.contas.get(n).consultar() - quantia < 0){
            throw new NotEnoughFunds("Fundos insuficientes!");
        }
        else this.contas.get(n).levantar(quantia);
    }

    public void transferir(double quantia, int n1, int n2) throws InvalidAccount, NotEnoughFunds{
        int conta_menor_id = Math.min(n1,n2);
        int conta_maior_id = Math.max(n1,n2);
        synchronized (contas.get(conta_menor_id)){
            synchronized (contas.get(conta_maior_id)){
                this.debito(n1,quantia);
                this.credito(n2,quantia);
            }
        }
    }

    public int createAccount(double saldo){
        this.lockBanco.lock();
        int id = lastID++;
        Conta c = new Conta(id,saldo);
        this.contas.put(id,c);
        this.lockBanco.unlock();

        return id;
    }

    public double closeAccount(int id) throws InvalidAccount{
        try{
            this.lockBanco.lock();
            if(this.contas.containsKey(id)){
                Conta c = this.contas.get(id);
                c.lock();
                double saldo = c.consultar();
                this.contas.remove(id);
                c.unlock();
                this.lockBanco.unlock();
                return saldo;
            }
            else {
                this.lockBanco.unlock();
                throw new InvalidAccount("Conta Inválida!");
            }
        }
        catch(Exception e){
            throw new InvalidAccount("Conta Inválida!");
        }
    }

    public double totalBalance(int[] accounts) throws InvalidAccount {
        double saldoTotal = 0;
        this.lockBanco.lock();
        for(int i = 0 ; i < accounts.length; i++){
            int id = accounts[i];
            if(this.contas.containsKey(id)){
                this.contas.get(id).lock();
                saldoTotal += this.consulta(id);
                this.contas.get(id).unlock();
            }
            else{
                throw new InvalidAccount("Conta Inválida!");
            }
        }
        this.lockBanco.unlock();
        return saldoTotal;
    }

    public List<Movimento> movimentos(int id) throws InvalidAccount{
        if(!this.contas.containsKey(id))
            throw new InvalidAccount("Conta Inválida!");
        else return this.contas.get(id).getMovimentos();
    }

    public void addMovimento(int id, int id_op, String descritivo, double saldo_final) throws InvalidAccount{
        if(!this.contas.containsKey(id))
            throw new InvalidAccount("Conta Inválida!");
        else this.contas.get(id).addMovimento(id_op,descritivo,saldo_final);
    }
}