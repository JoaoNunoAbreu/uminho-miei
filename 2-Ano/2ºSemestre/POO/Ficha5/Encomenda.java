import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
/**
 * Escreva a descrição da classe Encomenda aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Encomenda
{
    private String nome;
    private int numCliente;
    private String morada;
    private int numEncomenda;
    private LocalDate data;
    private List<LinhaEncomenda> lista;
    public Encomenda(){
        this.nome = "n/a";
        this.numCliente = 0;
        this.morada = "n/a";
        this.numEncomenda = 0;
        this.data = LocalDate.now();
        this.lista = new ArrayList<>();
    }
    public Encomenda(String nome, int numCliente, String morada, int numEncomenda, LocalDate data,List<LinhaEncomenda> lista){
        this.nome = nome;
        this.numCliente = numCliente;
        this.morada = morada;
        this.numEncomenda = numEncomenda;
        this.data = data;
        setLista(lista);
    }
    public Encomenda(Encomenda outraEncomenda){
        this.nome = outraEncomenda.getNome();
        this.numCliente = outraEncomenda.getNumCliente();
        this.morada = outraEncomenda.getMorada();
        this.numEncomenda = outraEncomenda.getNEnc();
        this.data = outraEncomenda.getData();
        this.lista = outraEncomenda.getLista();
    }
    public String getNome(){
        return this.nome;
    }
    public int getNumCliente(){
        return this.numCliente;
    }
    public String getMorada(){
        return this.morada;
    } 
    public int getNEnc(){
        return this.numEncomenda;
    }
    public LocalDate getData(){
        return this.data;
    }
    public List<LinhaEncomenda> getLista(){
        List<LinhaEncomenda> res = new ArrayList<>();
        lista.stream().forEach(s -> res.add(s));
        return res;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setNumCliente(int numCliente){
        this.numCliente = numCliente;
    }
    public void setMorada(String morada){
        this.morada = morada;
    }
    public void setNumEncomenda(int numEncomenda){
        this.numEncomenda = numEncomenda;
    }
    public void setData(LocalDate data){
        this.data = data;
    }
    public void setLista(List<LinhaEncomenda> a){
        this.lista = new ArrayList<>();
        for(LinhaEncomenda s : a)
            this.lista.add(s);
    }
    public Encomenda clone(){
        return new Encomenda(this);
    }
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null | o.getClass() != this.getClass()) return false;
        Encomenda e = (Encomenda) o;
        return this.nome.equals(e.getNome()) && this.numCliente == e.getNumCliente() && this.morada.equals(e.getMorada()) && this.numEncomenda == e.getNEnc() && this.data.equals(e.getData()) && this.lista.equals(e.getLista());
    }
    public String toString(){
        return "Nome: " + this.nome + "\nNIF: " + this.numCliente + "\nMorada: " + this.morada + "\nNumEncomenda: " + this.numEncomenda + "\nData: " + this.data.toString() + "\nLista: " + this.lista.toString();
    }
    public double calculaValorTotal(){
        double sum = 0;
        for(int i = 0; i < this.lista.size(); i++)
            sum += this.lista.get(i).calculaValorLinhaEnc();
        return sum;
    }
    public double calculaValorDesconto(){
        double sum = 0;
        for(int i = 0; i < this.lista.size(); i++)
            sum += this.lista.get(i).calculaValorDesconto();
        return sum;
    }
    public int numeroTotalProdutos(){
        int sum = 0;
        for(int i = 0; i < this.lista.size(); i++)
            sum += this.lista.get(i).getQuantidade();
        return sum;
    }
    public boolean existeProdutoEncomenda(String refProduto){
        boolean r = false;
        for(int i = 0; i < this.lista.size() && r == false; i++)
            if(this.lista.get(i).getReferencia().equals(refProduto)) r = true;
        return r;
    }
    public void adicionaLinha(LinhaEncomenda linha){
        this.lista.add(linha);
    }
    public void removeProduto(String codProd){
        for(int i = 0; i < this.lista.size(); i++)
            if(this.lista.get(i).getReferencia().equals(codProd)) this.lista.remove(i);
    }
}
