import java.lang.Math;
/**
 * Escreva a descrição da classe Circulo aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Circulo{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private double x;
    private double y;
    private double r;
    
    /**
     * COnstrutor para objetos da classe Circulo
     */
    public Circulo(){
        this.x = this.y = 0;
        this.r = 1;
    }
    public Circulo(double x, double y, double r){
        this.x = x;
        this.y = y;
        this.r = r;
    }
    public Circulo(Circulo outroCirculo){
        this.x = outroCirculo.getX();
        this.y = outroCirculo.getY();
        this.r = outroCirculo.getR();
    }
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Circulo c = (Circulo) o;
        return (this.x == c.getX() && this.y == c.getY() && this.r == c.getR());
    }
    public String toString(){
        return ("Centro: (" + this.x + "," + this.y + ") com raio " + this.r);
    }
    public Circulo clone() {
        return new Circulo(this);    
    }
    // (a)
    public double getX(){
        return this.x;
    }
    // (b)
    public double getY(){
        return this.y;
    }
    // (c)
    public double getR(){
        return this.r;
    }
    // (d)
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }
    public void setR(double r){
        this.r = r;
    }
    // (e)
    public void alteraCentro(double x, double y){
        setX(x);
        setY(y);
    }
    // (f)
    public double calculaArea(){
        return Math.PI*Math.pow(this.r,2.0);
    }
    // (g)
    public double calculaPerimetro(){
        return 2*Math.PI*this.r;
    }
}
