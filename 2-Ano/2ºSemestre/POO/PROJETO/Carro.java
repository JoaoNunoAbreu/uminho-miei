import java.awt.Point;
import java.lang.Math;
import java.util.*;
import java.time.LocalDate;
import java.io.Serializable;
public abstract class Carro implements Serializable{
    private String marca;
    private String matricula;
    private int nif;
    private double velocidadeMediaKm;
    private double precoKm;
    private double consumoPorKm;
    private Point.Double posicao;
    private double classificacao;
    private boolean disponibilidade;
    private List<Aluguer> al;
    
    private static int num_vezes_alugado = 0;
    public Carro(){
        this.marca = "N/A";
        this.matricula = "N/A";
        this.nif = 0;
        this.velocidadeMediaKm = 0;
        this.precoKm = 0;
        this.consumoPorKm = 0;
        this.posicao = new Point.Double();
        this.classificacao = 0;
        this.disponibilidade = true;
        this.al = new ArrayList<>();
    }
    public Carro(String marca,String matr, int nif, double velocidadeMediaKm,double precoKm,double consumoPorKm,Point.Double posicao, int classificacao,boolean disponibilidade,List<Aluguer> al){
        this.marca = marca;
        this.matricula = matr;
        this.nif = nif;
        this.velocidadeMediaKm = velocidadeMediaKm;
        this.consumoPorKm = consumoPorKm;
        this.precoKm = precoKm;
        setAl(al);
        this.classificacao = classificacao;
        this.disponibilidade = disponibilidade;
        this.posicao = posicao;
    }
    public Carro(Carro carro){
        this.marca = carro.getMarca();
        this.matricula = carro.getMatricula();
        this.nif  = carro.getNif();
        this.velocidadeMediaKm = carro.getVelMedKm();
        this.consumoPorKm = carro.getConsumoKm();
        this.precoKm = carro.getPrecoKm();
        this.al = carro.getAl();
        this.classificacao = carro.getClassi();
        this.disponibilidade = carro.getDisponibilidade();
        this.posicao = carro.getPosicao();
    }
    public void setMarca(String marca){
        this.marca = marca;
    }
    public void setMatricula(String mat){
        this.matricula = mat;
    }
    public void setNif(int nif){
        this.nif = nif;
    }
    public void setVelMedKm(double velocidadeMediaKm){
       this.velocidadeMediaKm = velocidadeMediaKm;
    }
    public void setPrecoKm(double precoKm){
        this.precoKm = precoKm;
    }
    public void setConsumoKm(double consumoPorKm){
       this.consumoPorKm = consumoPorKm;
    }
    public void setPosicao(Point.Double posicao){
        this.posicao = posicao;
    }
    public void setClassificacao(double classificacao){
       this.classificacao = classificacao;
    }
    public void setDisponibilidade(boolean r){
        this.disponibilidade = r;
    }
    public void setAl(List<Aluguer> l){
        this.al = new ArrayList<>();
        for(Aluguer a : l)
            this.al.add(a);    
    }
    public String getMarca(){
        return this.marca;
    }
    public String getMatricula(){
        return this.matricula;
    }
    public int getNif(){
        return this.nif;
    }
    public double getVelMedKm(){
        return this.velocidadeMediaKm;
    }
    public double getPrecoKm(){
        return this.precoKm;
    }
    public double getConsumoKm(){
        return this.consumoPorKm;
    }
    public Point.Double getPosicao(){
        return this.posicao;
    }
    public double getClassi(){
        return this.classificacao;
    }
    public boolean getDisponibilidade(){
        return this.disponibilidade;
    }
    public List<Aluguer> getAl(){
        List<Aluguer> res = new ArrayList<>();
        for(Aluguer a : this.al)
            res.add(a);
        return res;
    }
    @Override
    public abstract Carro clone();
    @Override
    public String toString(){
        return "Marca:" + this.marca
              + ",Nif: " + this.nif
              +",Matricula: " + this.matricula 
              +",VelocidadeMedia:" + this.velocidadeMediaKm
              +",Preço por Km: " + this.precoKm 
              +",Consumo por Km: " +  this.consumoPorKm
              +",Histórico: " +  this.al.toString()
              +",Classificação: " + this.classificacao
              +",Posição: " + this.posicao.toString()
              +",Disponibilidade: " + this.disponibilidade;
    }
    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Carro c = (Carro) o;
        return(this.marca.equals(c.getMarca()) 
            && this.matricula.equals(c.getMatricula())
            && this.nif == c.getNif()
            && this.velocidadeMediaKm==c.getVelMedKm()
            && this.precoKm == c.getPrecoKm()
            && this.consumoPorKm==c.getConsumoKm()
            && this.al.equals(c.getAl())
            && this.classificacao==c.getClassi()
            && this.posicao.equals(c.getPosicao()));
    }
    public boolean check(Point.Double dest){
        if(this.getAutonomia() < this.consumoPorKm*this.posicao.distance(dest)
        || this.getAutonomia() < this.getAutonomia_init() * 0.1
        || this.disponibilidade == false)
            return false;
        return true;
    }
    public abstract double getAutonomia();
    public abstract double getAutonomia_init();
    public abstract void setAutonomia(double autonomia);
    /**
     * Calcula a nova média da classificação
     */
    public double media(double rate){
        this.num_vezes_alugado++;
        return this.classificacao * (num_vezes_alugado-1/num_vezes_alugado)
               + rate * (1/num_vezes_alugado);
    }
    /**
     * Adiciona um aluguer
     */
    public void addAluguer(Aluguer a){
        this.al.add(a.clone());
    }
    /**
     * Get proprietário usando a key nif
     */
    public Proprietario getProprietario(UMCarroJa u){
        return u.getProprietarios().get(nif);
    }
    /**
     * Total facturado dos alugueres efectuados entre datas
     */
    public double facturadoEntreDatas(LocalDate inicio, LocalDate fim){
        double res = 0;
        for(Aluguer a : this.al)
            if(a.getData().isAfter(inicio) && a.getData().isBefore(fim)){
                res += a.getPreco();
            }
        return res;
    }
    /**
     * Preço de uma viagem 
     */
    public double preco(Point.Double dest){
        return this.precoKm / this.posicao.distance(dest);
    }
    /**
     * Tempo de uma viagem
     */
    public double tempo(Point.Double dest){
        return this.posicao.distance(dest) / this.velocidadeMediaKm;
    }
    /**
     * Calcula nova autonomia após uma viagem
     */
    public double novaAutonomia(Point.Double dest){
        return this.getAutonomia() - this.consumoPorKm * this.posicao.distance(dest);
    }
    /**
     * Formato CSV
     */
    public String toCSV(){
        return this.marca + ","
              + this.nif + ","
              + this.matricula + ","
              + this.velocidadeMediaKm+ ","
              + this.precoKm + ","
              + this.consumoPorKm + ","
              + this.al.toString() + ","
              + this.classificacao + ","
              + this.posicao.getX() + ","
              + this.posicao.getY() + ","
              + this.disponibilidade;
    }
}
