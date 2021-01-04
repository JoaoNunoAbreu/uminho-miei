
/**
 * Escreva a descrição da classe Ponto3D aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Ponto3D extends Ponto
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private int z;
    
    /**
     * COnstrutor para objetos da classe Ponto3D
     */
    public Ponto3D(){
        super();
        this.z = 0;
    }
    public Ponto3D(int x, int y, int z){
        super(x,y);
        this.z = z;
    }
    public Ponto3D(Ponto3D ponto){
        super(ponto);
        this.z = ponto.getZ();
    }
    public int getZ(){
        return this.z;
    }
    public void setZ(int z){
        this.z = z;
    }
    
    @Override
    public Ponto3D clone(){
        return new Ponto3D(this);
    }
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass())
        return false;
        Ponto3D p = (Ponto3D) o;
        return super.equals(p) && p.getZ() == this.z;
    }
    @Override
    public String toString(){
        return super.toString() + " z = " + this.z;
    }
    public void movePonto(int x, int y, int z){
        super.movePonto(x,y);
        this.z = z;
    }
    
}
