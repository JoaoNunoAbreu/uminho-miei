import java.awt.Point;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.io.Serializable;
public class Cliente extends Actor implements Comparable<Cliente>{
    private Point.Double posicao;
    private static double velocidade_a_pe = 4;
    public Cliente(){
        super();
        this.posicao = new Point.Double();
    }
    public Cliente(String email,String nome,String password,String morada,LocalDate data,double classificacao, int nif, List<Aluguer> hist, Point.Double posicao){
        super(email,nome,password,morada,data,classificacao,nif,hist);
        this.posicao = posicao;
    }
    public Cliente(Cliente c){
        super(c);
        setHist(c.getHist());
        this.posicao = c.getPosicao();
    }
    public Point.Double getPosicao(){
        return this.posicao;
    }
    public void setPosicao(Point.Double posicao){
        this.posicao = posicao;
    }
    public int compareTo(Cliente c){
        return this.getNome().compareTo(c.getNome());
    }
    @Override
    public String toString(){
        return super.toString() + ",Posição: " + this.posicao.toString();
    }
    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Cliente c = (Cliente) o;
        return super.equals(c) && this.posicao.equals(c.getPosicao());
    }
    @Override
    public Cliente clone(){
        return new Cliente(this);
    }
    /**
     * Solicitar o carro mais próximo das suas coordenadas
     */
    public Carro carroMaisPerto(UMCarroJa ucj, Point.Double dest) throws NullPointerException{
        double min = Double.MAX_VALUE;
        Carro res = null;
        for(Carro c: ucj.getCarros().values()){
            if(this.posicao.distance(c.getPosicao()) < min && c.check(dest)){
                min = this.posicao.distance(c.getPosicao());
                res = c.clone();
            }
        }
        return res;
    }
    /**
     * Solicitar o carro mais perto do cliente de um determinado tipo de combustível
     * Usado no carregamento inicial
     */
    public Carro carroMaisPerto(UMCarroJa ucj,String tipo,Point.Double dest) throws NullPointerException{
        double min = Double.MAX_VALUE;
        Carro res = null;
        for(Carro c: ucj.getCarros().values()){
            if(this.posicao.distance(c.getPosicao()) < min && c.check(dest)){
                if(tipo.equals("Gasolina")){
                    if(c instanceof CarroGasolina){
                        min = this.posicao.distance(c.getPosicao());
                        res = c.clone();
                    }
                }
                else if(tipo.equals("Electrico")){
                    if(c instanceof CarroEletrico){
                        min = this.posicao.distance(c.getPosicao());
                        res = c.clone();
                    }
                }
                else if(tipo.equals("Hibrido")){
                    if(c instanceof CarroHibrido){
                        min = this.posicao.distance(c.getPosicao());
                        res = c.clone();
                    }
                }
            }
        }
        return res;
    }
    /**
     * Solicitar o carro mais barato
     */
    public Carro carroMaisBarato(UMCarroJa ucj, Point.Double dest) throws NullPointerException{
        double min = Double.MAX_VALUE;
        Carro res = null;
        for(Carro c: ucj.getCarros().values()){
            if(c.getPrecoKm() < min && c.check(dest)){
                min = c.getPrecoKm();
                res = c;
            }
        }
        return res;
    }
    /**
     * Solicitar o carro mais barato de um determinado tipo de combustível
     * Usado no carregamento inicial
     */
    public Carro carroMaisBarato(UMCarroJa ucj, String tipo, Point.Double dest) throws NullPointerException{
        double min = Double.MAX_VALUE;
        Carro res = null;
        for(Carro c: ucj.getCarros().values()){
            if(c.getPrecoKm() < min && c.check(dest)){
                if(tipo.equals("Gasolina")){
                    if(c instanceof CarroGasolina){
                        min = c.getPrecoKm();
                        res = c.clone();
                    }
                }
                else if(tipo.equals("Electrico")){
                    if(c instanceof CarroEletrico){
                        min = c.getPrecoKm();
                        res = c.clone();
                    }
                }
                else if(tipo.equals("Hibrido")){
                    if(c instanceof CarroHibrido){
                        min = c.getPrecoKm();
                        res = c.clone();
                    }
                }
            }
        }
        return res;
    }
    /**
     * Solicitar o carro mais barato dentro de uma distância que estão dispostos a percorrer a pé
     */
    public Carro carroMaisBaratoDistancia(UMCarroJa ucj, Point.Double dest, double distancia)throws NullPointerException{
        double min = Double.MAX_VALUE;
        Carro res = null;
        for(Carro c: ucj.getCarros().values()){
            if(c.getPrecoKm() < min && c.check(dest) && c.getPosicao().distance(dest) < distancia){
                min = c.getPrecoKm();
                res = c;
            }
        }
        return res;
    }
    /**
     * Solicitar um carro específico
     */
    public Carro carroEspecifico(UMCarroJa ucj, Point.Double dest, Carro carro_especifico)throws NullPointerException{
        Carro res = null;
        for(Carro c: ucj.getCarros().values()){
            if(c.check(dest) && c.equals(carro_especifico)){
                res = c;
            }
        }
        return res;
    }
    /**
     * Solicitar um carro com uma autonomia desejada
     */
    public Carro carroEspecificoAutonomia(UMCarroJa ucj, Point.Double dest, Carro carro_especifico, double autonomia) throws NullPointerException{
        Carro res = null;
        for(Carro c: ucj.getCarros().values()){
            if(c.check(dest) && c.equals(carro_especifico) && c.getAutonomia() == autonomia){
                res = c;
            }
        }
        return res;
    }
    /**
     * Tempo que demora um cliente a um ponto a pé
     */
    public double tempoAPe(Point.Double dest){
        return this.posicao.distance(dest) / velocidade_a_pe;
    }
    /**
     * Calcula o número de kms percorridos no histórico de alugueres
     */
    public double kmsPercorridos(){
        double sum = 0;
        for(Aluguer a : this.getHist())
            sum += a.getPosI().distance(a.getPosF());
        return sum;
    }
    /**
     * Atribui uma nova classificação a um carro
     */
    public void classificar(double classificacao, Carro c){
        c.setClassificacao(c.media(classificacao));
    }
    /**
     * Atribui uma nova classificação a um proprietário
     */
    public void classificar(double classificacao, Proprietario p){
        p.setClassificacao(p.media(classificacao));
    }
    /**
     * Formato CSV
     */
    public String toCSV(){
        return super.toCSV() + "," + this.posicao.getX() + "," + this.posicao.getY();
    }
}