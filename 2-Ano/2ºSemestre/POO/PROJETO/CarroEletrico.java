import java.awt.Point;
import java.util.List;
public class CarroEletrico extends Carro{
    private double autonomiaEletro;
    private static double autonomiaEletro_init;
    public CarroEletrico(){
        super();
        this.autonomiaEletro = 0;
    }
    public CarroEletrico(String marca,String matr, int nif, double velocidadeMediaKm,double precoKm,double consumoPorKm,Point.Double posicao, int classificacao,boolean disponibilidade,List<Aluguer> al, int autonomiaEletro){
        super(marca,matr,nif,velocidadeMediaKm,precoKm,consumoPorKm,posicao,classificacao,disponibilidade,al);
        this.autonomiaEletro_init = autonomiaEletro;
        this.autonomiaEletro = autonomiaEletro;
    }
    public CarroEletrico(CarroEletrico c){
        super(c);
        this.autonomiaEletro = c.getAutonomia();
    }
    public void setAutonomia(double autonomia){
        if(autonomia > 1000) autonomia = 1000;
        this.autonomiaEletro = autonomia;
    }
    public double getAutonomia(){
        return this.autonomiaEletro;
    }
    public double getAutonomia_init(){
        return this.autonomiaEletro_init;
    }
    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        
        CarroEletrico c = (CarroEletrico) o;
        return super.equals(c) && this.autonomiaEletro == c.getAutonomia();
    }
    @Override
    public String toString(){
        return super.toString() + ",Autonomia: " + this.autonomiaEletro;
    }
    @Override
    public CarroEletrico clone(){
        return new CarroEletrico(this);
    }
    public String toCSV(){
        return super.toCSV() + "," + this.autonomiaEletro;
    }
}
