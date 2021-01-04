import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.io.Serializable;
public abstract class Actor implements Serializable{
    private String email;
    private String nome;
    private String password;
    private String morada;
    private LocalDate dataDeNascimento;
    private double classificacao;
    private int nif;
    private List<Aluguer> hist;
    
    private static int num_classificacoes = 0;
    public Actor(){
        this.email = "N/A";
        this.nome = "N/A";
        this.password = "N/A";
        this.morada = "N/A";
        this.dataDeNascimento = LocalDate.now();
        this.classificacao = 0;
        this.nif = 0;
        this.hist = new ArrayList<>();
    }
    public Actor(String email,String nome,String password,String morada,LocalDate data,double classificacao, int nif, List<Aluguer> hist){
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.setData(data);
        this.classificacao = classificacao;
        this.nif = nif;
        setHist(hist);
    }
    public Actor(Actor a){
        this.email = a.getEmail();
        this.nome = a.getNome();
        this.password = a.getPassword();
        this.morada = a.getMorada();
        this.dataDeNascimento = a.getDataDeNascimento();
        this.classificacao = a.getClassificacao();
        this.nif = a.getNif();
        setHist(a.getHist());
    }
    public String getEmail(){
        return this.email;
    }
    public String getNome(){
        return this.nome;
    }
    public String getPassword(){
        return this.password;
    }
    public String getMorada(){
        return this.morada;
    }
    public LocalDate getDataDeNascimento(){
        return this.dataDeNascimento;
    }
    public double getClassificacao(){
        return this.classificacao;
    }
    public int getNif(){
        return this.nif;
    }
    public List<Aluguer> getHist(){
        List<Aluguer> res = new ArrayList<>();
        for(Aluguer a : this.hist)
            res.add(a);
        return res;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setNome(String nome){
        this.nome=nome;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setMorada(String morada){
        this.morada = morada;
    }
    public void setData(LocalDate data){
        this.dataDeNascimento = data;
    }
    public void setClassificacao(double classificacao){
        this.classificacao = classificacao;
    }
    public void setNif(int nif){
        this.nif=nif;
    }
    public void setHist(List<Aluguer> l){
        this.hist = new ArrayList<>();
        for(Aluguer a : l)
            this.hist.add(a);    
    }
    @Override
    public abstract Actor clone();
    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Actor a = (Actor) o;
        return(this.email.equals(a.getEmail()) && this.nome.equals(a.getNome()) &&
        this.password.equals(a.getPassword()) && this.morada.equals(a.getMorada()) &&
        this.dataDeNascimento.equals(a.getDataDeNascimento()) && this.classificacao == a.getClassificacao()
        && this.nif == a.getNif() && this.hist.equals(a.getHist()));
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Email: ").append(this.email)
          .append(",Nome: ").append(this.nome)
          .append(",Password: ").append(this.password)
          .append(",Morada: ").append(this.morada)
          .append(",Data de nascimento: ").append(this.dataDeNascimento.toString())
          .append(",Classificação: ").append(this.classificacao)
          .append(",Nif: ").append(this.nif)
          .append(",Histórico: ").append(this.hist.toString());
        return sb.toString();
    }
    /**
     * Calcula a nova média da classificação
     */
    public double media(double rate){
        this.num_classificacoes++;
        return this.classificacao * (num_classificacoes-1/num_classificacoes)
               + rate * (1/num_classificacoes);
    }
    /**
     * Listagem dos alugueres efectuados entre datas
     */
    public List<Aluguer> getHist_entreDatas(LocalDate inicio, LocalDate fim){
        List<Aluguer> aluList = new ArrayList<>();
        for(Aluguer a : this.hist)
            if(a.getData().isAfter(inicio) && a.getData().isBefore(fim)){
                aluList.add(a.clone());
            }
        return aluList;
    }
    /**
     * Adiciona um aluguer
     */
    public void addAluguer(Aluguer a){
        this.hist.add(a.clone());
    }
    /**
     * Formato CSV
     */
    public String toCSV(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.email).append(",")
          .append(this.nome).append(",")
          .append(this.password).append(",")
          .append(this.morada).append(",")
          .append(this.dataDeNascimento.toString()).append(",")
          .append(this.classificacao).append(",")
          .append(this.nif).append(",")
          .append(this.hist.toString());
        return sb.toString();
    } 
}