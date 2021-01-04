import java.io.Serializable;
public class HotelDiscount extends Hotel implements Serializable{
    private double ocupacao;
    public HotelDiscount(){
        super();
        this.ocupacao = 0;
    }
    public HotelDiscount(int id, String nome, String localidade, int categoria, int quartosDisponiveis,double precoPorQuarto, double ocupacao){
        super(id,nome,localidade,categoria,quartosDisponiveis,precoPorQuarto);
        this.ocupacao = ocupacao;
    }
    public HotelDiscount(HotelDiscount h){
        super(h);
        this.ocupacao = h.getOcupacao();
    }
    @Override
    public HotelDiscount clone(){
        return new HotelDiscount(this);
    }
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass())
        return false;
        HotelDiscount h = (HotelDiscount) o;
        return super.equals(h) && h.getOcupacao() == this.ocupacao;
    }
    @Override
    public String toString(){
        return super.toString() + "Ocupacao: " + this.ocupacao + "\n";
    }
    public double getOcupacao(){
        return this.ocupacao;
    }
    public void setOcupacao(double ocupacao){
        this.ocupacao = ocupacao;
    }
    public double precoNoite(){
        return super.getPrecoPorQuarto() * (this.ocupacao + 1); 
    }
    public String toCSV(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toCSV());
        sb.append(",");
        sb.append(this.ocupacao);
        return sb.toString();
    }
}
