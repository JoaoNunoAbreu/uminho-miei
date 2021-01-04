import java.lang.Math;
/**
 * Escreva a descrição da classe Triangulo aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Triangulo{
    private Ponto a;
    private Ponto b;
    private Ponto c;
    public Triangulo(){
        this.a = new Ponto();
        this.b = new Ponto();
        this.c = new Ponto();
    }
    public Triangulo(Ponto a, Ponto b, Ponto c){
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public Triangulo(Triangulo outroTriangulo){
        this.a = outroTriangulo.getA();
        this.b = outroTriangulo.getB();
        this.c = outroTriangulo.getC();
    }
    public Ponto getA(){
        return this.a;
    }
    public Ponto getB(){
        return this.b;
    }
    public Ponto getC(){
        return this.c;
    }
    public void setA(Ponto a){
        this.a = a;
    }
    public void setB(Ponto b){
        this.b = b;
    }
    public void setC(Ponto c){
        this.c = c;
    }
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Triangulo t = (Triangulo) o;
        return (this.a.equals(t.getA()) && this.b.equals(t.getB()) && this.c.equals(t.getC()));
    }
    public Triangulo clone(){
        return new Triangulo(this);
    }
    public String toString(){
        return ("A: " + this.a.toString() + "\nB: " + this.b.toString() + "\nC: " + this.c.toString());
    }
    public double calculaAreaTriangulo(){
        double dist1 = this.a.distancia(this.b);
        double dist2 = this.b.distancia(this.c);
        double dist3 = this.c.distancia(this.a);
        // Heron's Formula
        double p = (dist1 + dist2 + dist3)/2; 
        return Math.sqrt(p*(p-dist1)*(p-dist2)*(p-dist3));
    }
    public double calculaPerimetroTriangulo(){
        double dist1 = this.a.distancia(this.b);
        double dist2 = this.b.distancia(this.c);
        double dist3 = this.c.distancia(this.a);
        return dist1+dist2+dist3;
    }
    public int alturaTriangulo(){
        int min = Math.min(this.a.getY(),Math.min(this.b.getY(),this.c.getY()));
        int max = Math.max(this.a.getY(),Math.max(this.b.getY(),this.c.getY()));
        return max - min;
    }
}
