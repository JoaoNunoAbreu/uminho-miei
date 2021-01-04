import java.awt.Point;
import java.util.List;
public class CarroHibrido extends Carro{
    private double autonomiaComb;
    private double autonomiaEletro;
    private static double autonomiaComb_init;
    private static double autonomiaEletro_init;
    public CarroHibrido(){
        super();
        this.autonomiaComb = 0;
        this.autonomiaEletro = 0;
    }
    public CarroHibrido(String marca,String matr, int nif, double velocidadeMediaKm,double precoKm,double consumoPorKm,Point.Double posicao, int classificacao,boolean disponibilidade,List<Aluguer> al, double autonomiaComb, double autonomiaEletro){
        super(marca,matr,nif,velocidadeMediaKm,precoKm,consumoPorKm,posicao,classificacao,disponibilidade,al);
        this.autonomiaComb_init = autonomiaComb;
        this.autonomiaEletro_init = autonomiaEletro;
        this.autonomiaComb = autonomiaComb;
        this.autonomiaEletro = autonomiaEletro;
    }
    public CarroHibrido(CarroHibrido c){
        super(c);
        this.autonomiaComb = c.getAutonomiaComb();
        this.autonomiaEletro = c.getAutonomiaEletro();
    }
    public void setAutonomiaComb(double autonomia){
        if(autonomia > 500) autonomia = 500;
        this.autonomiaComb = autonomia;
    }
    public void setAutonomiaEletro(double autonomia){
        if(autonomia > 500) autonomia = 500;
        this.autonomiaEletro = autonomia;
    }
    public void setAutonomia(double autonomia){
        if(autonomia > 1000) autonomia = 1000;
        setAutonomiaComb(autonomia/2);
        setAutonomiaEletro(autonomia/2);
    }
    public double getAutonomiaComb(){
        return this.autonomiaComb;
    }
    public double getAutonomiaEletro(){
        return this.autonomiaEletro;
    }
    public double getAutonomia(){
        return this.autonomiaEletro + this.autonomiaComb;
    }
    public double getAutonomia_init(){
        return this.autonomiaEletro_init + this.autonomiaComb_init;
    }
    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        
        CarroHibrido c = (CarroHibrido) o;
        return super.equals(c) && this.autonomiaComb == c.getAutonomiaComb() && this.autonomiaEletro == c.getAutonomiaEletro();
    }
    @Override
    public String toString(){
        return super.toString() + ",Autonomia combustível: " + this.autonomiaEletro + ",Autonomia elétrica:" + this.autonomiaEletro;
    }
    @Override
    public CarroHibrido clone(){
        return new CarroHibrido(this);
    }
    public String toCSV(){
        return super.toCSV() + "," + this.autonomiaComb + "," + this.autonomiaEletro;
    }
}
