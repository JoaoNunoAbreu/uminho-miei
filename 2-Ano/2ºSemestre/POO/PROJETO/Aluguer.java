import java.time.LocalDate;
import java.awt.Point;
import java.io.Serializable;
public class Aluguer implements Serializable{
    private Point.Double posI;
    private Point.Double posF;
    private double preco;
    private double tempo;
    private int nifCliente;
    private LocalDate data;
    private String preferencia;
    public Aluguer(){
        this.posI = new Point.Double(0,0);
        this.posF = new Point.Double(0,0);
        this.preco = 0;
        this.tempo = 0;
        this.nifCliente = 0;
        this.data = LocalDate.now();
        this.preferencia = "N/A";
    }
    public Aluguer(Point.Double posI, Point.Double posF,double preco, double tempo, int nifCliente, LocalDate data, String preferencia){
        this.posI = posI;
        this.posF = posF;
        this.preco = preco;
        this.tempo = tempo;
        this.nifCliente = nifCliente;
        this.data = data;
        this.preferencia = preferencia;
    }
    public Aluguer(Aluguer a){
        this.posI = a.getPosI();
        this.posF = a.getPosF();
        this.preco = a.getPreco();
        this.tempo = a.getTempo();
        this.nifCliente = a.getNifCliente();
        this.data = a.getData();
        this.preferencia = a.getPreferencia();
    }
    public Point.Double getPosI(){
        return this.posI;
    }
    public Point.Double getPosF(){
        return this.posF; 
    }
    public double getPreco(){
        return this.preco; 
    }
    public double getTempo(){
        return this.tempo; 
    }
    public int getNifCliente(){
        return this.nifCliente;
    }
    public LocalDate getData(){
        return this.data;
    }
    public String getPreferencia(){
        return this.preferencia;
    }
    public void setPosI(Point.Double p){
        this.posI = p;
    }
    public void setPosF(Point.Double p){
        this.posF = p; 
    }
    public void setPreco(double p){
        this.preco = p;
    }    
    public void setTempo(double t){
        this.tempo = t;
    }
    public void setNifCliente(int nifCliente){
        this.nifCliente = nifCliente;
    }
    public void setData(LocalDate data){
        this.data = data;
    }
    public void setPreferencia(String preferencia){
        this.preferencia = preferencia;
    }
    @Override
    public Aluguer clone(){
        return new Aluguer(this);
    }
    @Override
    public String toString (){
        StringBuilder sb = new StringBuilder();
        sb.append("Posição Inicial: ").append(this.posI.toString())
          .append(",Posição Final: ").append(this.posF.toString())
          .append(",Preço: ").append(this.preco)
          .append(",Tempo: ").append(this.tempo)
          .append(",Nif Cliente: ").append(this.nifCliente)
          .append(",Data: ").append(this.data.toString())
          .append(",Preferência: ").append(this.preferencia);
        return sb.toString();
    }
    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Aluguer al = (Aluguer) o;
        return this.posI.equals(al.getPosI()) &&
               this.posF.equals(al.getPosF()) &&
               this.preco == al.getPreco() &&
               this.tempo == al.getTempo() &&
               this.nifCliente == al.getNifCliente() &&
               this.data.equals(al.getData()) &&
               this.preferencia.equals(al.getPreferencia());
    }
    public String toCSV(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.posI.toString()).append(",")
          .append(this.posF.toString()).append(",")
          .append(this.preco).append(",")
          .append(this.tempo).append(",")
          .append(this.nifCliente).append(",")
          .append(this.data.toString()).append(",")
          .append(this.preferencia);
        return sb.toString();
    }
}
