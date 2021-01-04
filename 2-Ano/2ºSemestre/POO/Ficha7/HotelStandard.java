import java.io.Serializable;
public class HotelStandard extends Hotel implements CartaoHoteis,Serializable{
    private boolean epocaAlta;
    private int pontos;
    public HotelStandard(){
        super();
        this.epocaAlta = false;
    }
    public HotelStandard(int id, String nome, String localidade, int categoria, int quartosDisponiveis,double precoPorQuarto, boolean epocaAlta){
        super(id,nome,localidade,categoria,quartosDisponiveis,precoPorQuarto);
        this.epocaAlta = epocaAlta;
    }
    public HotelStandard(HotelStandard h){
        super(h);
        this.epocaAlta = h.getEpoca();
    }
    @Override
    public HotelStandard clone(){
        return new HotelStandard(this);
    }
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass())
        return false;
        HotelStandard h = (HotelStandard) o;
        return super.equals(h) && h.getEpoca() == this.epocaAlta;
    }
    @Override
    public String toString(){
        return super.toString() + "Época alta: " + this.epocaAlta + "\n";
    }
    public boolean getEpoca(){
        return this.epocaAlta;
    }
    public void setEpoca(boolean epoca){
        this.epocaAlta = epoca;
    }
    public double precoNoite(){
        double res = super.getPrecoPorQuarto();
        if(this.getEpoca()) res += 20;
        return res;
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
        sb.append(this.epocaAlta);
        sb.append(",");
        sb.append(this.pontos);
        return sb.toString();
    }
}
