import java.awt.Point;
import java.util.List;
public class CarroGasolina extends Carro{
    private double autonomiaComb;
    private static double autonomiaComb_init;
    public CarroGasolina(){
        super();
        this.autonomiaComb = 0;
    }
    public CarroGasolina(String marca,String matr, int nif, double velocidadeMediaKm,double precoKm,double consumoPorKm,Point.Double posicao, int classificacao,boolean disponibilidade,List<Aluguer> al, int autonomiaComb){
        super(marca,matr,nif,velocidadeMediaKm,precoKm,consumoPorKm,posicao,classificacao,disponibilidade,al);
        this.autonomiaComb_init = autonomiaComb;
        this.autonomiaComb = autonomiaComb;
    }
    public CarroGasolina(CarroGasolina c){
        super(c);
        this.autonomiaComb = c.getAutonomia();
    }
    public void setAutonomia(double autonomia){
        if(autonomia > 1000) autonomia = 1000;
        this.autonomiaComb = autonomia;
    }
    public double getAutonomia(){
        return this.autonomiaComb;
    }
    public double getAutonomia_init(){
        return this.autonomiaComb_init;
    }
    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        
        CarroGasolina c = (CarroGasolina) o;
        return super.equals(c) && this.autonomiaComb == c.getAutonomia();
    }
    @Override
    public String toString(){
        return super.toString() + ",Autonomia: " + this.autonomiaComb;
    }
    @Override
    public CarroGasolina clone(){
        return new CarroGasolina(this);
    }
    public String toCSV(){
        return super.toCSV() + "," + this.autonomiaComb;
    }
}
