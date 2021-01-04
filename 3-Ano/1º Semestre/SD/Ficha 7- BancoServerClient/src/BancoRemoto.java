import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class BancoRemoto implements BancoRemotoInter{

    private BufferedReader br;
    private PrintWriter pw;

    public BancoRemoto(String host, int port) throws IOException{
        Socket s = new Socket(host,port);
        this.br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.pw = new PrintWriter(s.getOutputStream());
    }

    public int createAccount(double inicial) throws BancoRemotoException{
        pw.println("createAccount " + inicial);
        pw.flush();
        try{
            return Integer.parseInt(br.readLine());
        }
        catch(IOException e){
            throw new BancoRemotoException("Não foi possível ler resposta");
        }
    }

    public double closeAccount(int id) throws InvalidAccount, BancoRemotoException{
        pw.println("closeAccount " + id);
        pw.flush();
        try {
            String lido = br.readLine();
            if(lido.equals("Conta Inválida!"))
                throw new InvalidAccount("Conta Inválida!");
            else return Double.parseDouble(lido);
        }
        catch(IOException e){
            throw new BancoRemotoException("Não foi possível ler resposta");
        }
    }

    public void transferir(double quantia, int n1, int n2) throws InvalidAccount, NotEnoughFunds, BancoRemotoException{
        pw.println("transferir " + quantia + " " + n1 + " " + n2);
        pw.flush();
        try {
            String lido = br.readLine();
            if (lido.equals("Conta Inválida!"))
                throw new InvalidAccount("Conta Inválida!");
            else if (lido.equals("Fundos insuficientes!"))
                throw new NotEnoughFunds("Fundos insuficientes!");
        }
        catch(IOException e){
            throw new BancoRemotoException("Não foi possível ler resposta");
        }
    }

    // Coloca array de inteiros numa string cujos elementos do array são separados por vírgulas ([1,2,3] = "1,2,3")
    private String toStringSemEspacos(int[] a){
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < a.length; i++)
            if(i == a.length-1) res.append(a[i]);
            else res.append(a[i]).append(",");
        return res.toString();
    }

    public double totalBalance(int[] accounts) throws InvalidAccount, BancoRemotoException{
        pw.println("totalBalance " + this.toStringSemEspacos(accounts));
        pw.flush();
        try {
            String lido = br.readLine();
            if(lido.equals("Conta Inválida!"))
                throw new InvalidAccount("Conta Inválida!");
            else return Double.parseDouble(lido);
        }
        catch(IOException e){
            throw new BancoRemotoException("Não foi possível ler resposta");
        }
    }

    public void credito(int n, double quantia) throws InvalidAccount, BancoRemotoException{
        pw.println("credito " + n + " " + quantia);
        pw.flush();
        try{
            String lido = br.readLine();
            if(lido.equals("Conta Inválida!"))
                throw new InvalidAccount("Conta Inválida!");
        }
        catch (IOException e) {
            throw new BancoRemotoException("Não foi possível ler resposta");
        }
    }

    public void debito(int n, double quantia) throws InvalidAccount, NotEnoughFunds, BancoRemotoException{
        pw.println("debito " + n + " " + quantia);
        pw.flush();
        try {
            String lido = br.readLine();
            if (lido.equals("Conta Inválida!"))
                throw new InvalidAccount("Conta Inválida!");
            else if (lido.equals("Fundos insuficientes!"))
                throw new NotEnoughFunds("Fundos insuficientes!");
        }
        catch (IOException e) {
            throw new BancoRemotoException("Não foi possível ler resposta");
        }
    }

    public double consulta(int n) throws InvalidAccount, BancoRemotoException{
        pw.println("consultar " + n);
        pw.flush();
        try {
            String lido = br.readLine();
            if (lido.equals("Conta Inválida!"))
                throw new InvalidAccount("Conta Inválida!");
            else return Double.parseDouble(lido);
        }
        catch (IOException e) {
            throw new BancoRemotoException("Não foi possível ler resposta");
        }
    }

    public List<Movimento> movimentos(int id) throws InvalidAccount, BancoRemotoException{
        pw.println("movimento " + id);
        pw.flush();
        try {
            String lido = br.readLine();
            if (lido.equals("Conta Inválida!"))
                throw new InvalidAccount("Conta Inválida!");
            else {
                String[] movs = lido.split(",");
                List<Movimento> res = new ArrayList<>();
                for (int i = 0; i < movs.length; i++) {
                    res.add(new Movimento(movs[i]));
                }
                return res;
            }
        }
        catch (IOException e) {
            throw new BancoRemotoException("Não foi possível ler resposta");
        }
    }
}
