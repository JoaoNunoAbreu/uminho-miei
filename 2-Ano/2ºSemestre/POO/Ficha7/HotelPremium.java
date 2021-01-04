import java.io.Serializable;
public class HotelPremium extends Hotel implements CartaoHoteis,Serializable{
    private double taxa;
    private int pontos;
    public HotelPremium(){
        super();
        this.taxa = 0;
    }
    public HotelPremium(int id, String nome, String localidade, int categoria, int quartosDisponiveis,double precoPorQuarto, double taxa){
        super(id,nome,localidade,categoria,quartosDisponiveis,precoPorQuarto);
        this.taxa = taxa;
    }
    public HotelPremium(HotelPremium h){
        super(h);
        this.taxa = h.getTaxa();
    }
    @Override
    public HotelPremium clone(){
        return new HotelPremium(this);
    }
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass())
        return false;
        HotelPremium h = (HotelPremium) o;
        return super.equals(h) && h.getTaxa() == this.taxa;
    }
    @Override
    public String toString(){
        return super.toString() + "Taxa: " + this.taxa + "\n";
    }
    public double getTaxa(){
        return this.taxa;
    }
    public void setTaxa(double taxa){
        this.taxa = taxa;
    }
    public double precoNoite(){
        return super.getPrecoPorQuarto() * this.taxa;
    }
    public void setPontos(int pontos){
        this.pontos = pontos;
    }
    public int getPontos(){
        return this.pontos;
    }
    public String toCSV(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toCSV());
        sb.append(",");
        sb.append(this.taxa);
        sb.append(",");
        sb.append(this.pontos);
        return sb.toString();
    }
}
