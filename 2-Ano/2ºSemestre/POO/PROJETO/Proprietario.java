import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDate;
public class Proprietario extends Actor{
    private List<String> carros;
    public Proprietario(){
        super();
        this.carros = new ArrayList<>();
    }
    public Proprietario(String email,String nome,String password,String morada,LocalDate data,double classificacao, int nif, List<Aluguer> hist,List<String> carros){
        super(email,nome,password,morada,data,classificacao,nif,hist);
        setCarros(carros);
    }
    public Proprietario(Proprietario p){
        super(p);
        setCarros(p.getCarros());
    }
    public List<String> getCarros(){
        List<String> res = new ArrayList<>();
        for(String matr : this.carros)
            res.add(matr);
        return res;
    }
    public void setCarros(List<String> l){
        this.carros = new ArrayList<>();
        for(String matr : l)
            this.carros.add(matr);    
    }
    @Override
    public String toString(){
        return super.toString()
           + ",Carros registados: " + this.carros;
    }
    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Proprietario p = (Proprietario) o;
        return super.equals(p) && this.carros.equals(p.getCarros());
    }
    @Override
    public Proprietario clone(){
        return new Proprietario(this);
    }
    /**
     * Adiciona um carro
     */
    public void addCarro(Carro carro){
        this.carros.add(carro.getMatricula());
    }
    /**
     * Sinalizar que um dos seus carros está disponível/indisponível para aluguer
     */
    public void sinaliza(UMCarroJa ucj, Carro carro, boolean r){
        ucj.getCarros().get(carro.getMatricula()).setDisponibilidade(r);
    }
    /**
     * Abastecer o veiculo
     */
    public void abastecer(UMCarroJa ucj,Carro carro, double autonomia_nova){
        ucj.getCarros().get(carro.getMatricula()).setAutonomia(autonomia_nova);
    }
    /**
     * Alterar o preço por km
     */
    public void alterarPrecoKm(UMCarroJa ucj,Carro carro, double novo_preco){
        ucj.getCarros().get(carro.getMatricula()).setPrecoKm(novo_preco);
    }
    /**
     * Aceitar/rejeitar o aluguer de um determinado cliente
     */
    public boolean decideAluguer(Aluguer a,Carro carro){
        return carro.getDisponibilidade();
    }
    /**
     * Atribui uma nova classificação a um cliente
     */
    public void classificar(double classificacao, Cliente c) throws ActorNotFoundException{
        if(this.getHist().stream().map(Aluguer :: getNifCliente).collect(Collectors.toList()).contains(c.getNif()))
            c.setClassificacao(c.media(classificacao));
        else throw new ActorNotFoundException("O cliente não alugou nenhum carro deste proprietário");
    }
    /**
     * Formato CSV
     */
    public String toCSV(){
        return super.toCSV() + "," + this.carros;
    }
}
