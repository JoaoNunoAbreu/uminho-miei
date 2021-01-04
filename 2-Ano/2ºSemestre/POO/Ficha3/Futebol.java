
/**
 * Escreva a descrição da classe Futebol aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Futebol
{
    private int estado; // 0 por iniciar, 1 a decorrer e 2 terminado.
    private int golosVisitado;
    private int golosVisitante;
    
    public Futebol(){
        this.estado = 0;
        this.golosVisitado = 0;
        this.golosVisitante = 0;
    }
    public Futebol(int estado, int golosVisitado, int golosVisitante){
        setEstado(estado);
        setGolosVisitado(golosVisitado);
        setGolosVisitante(golosVisitante);
    }
    public Futebol(Futebol outroFutebol){
        this.estado = outroFutebol.getEstado();
        this.golosVisitado = outroFutebol.getGolosVisitado();
        this.golosVisitante = outroFutebol.getGolosVisitante();
    }
    public int getEstado(){
        return this.estado;
    }
    public int getGolosVisitado(){
        return this.golosVisitado;
    }
    public int getGolosVisitante(){
        return this.golosVisitante;
    }
    public void setEstado(int estado){
        this.estado = estado;
    }
    public void setGolosVisitado(int golosVisitado){
        this.golosVisitado = golosVisitado;
    }
    public void setGolosVisitante(int golosVisitante){
        this.golosVisitante = golosVisitante;
    }
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Futebol f = (Futebol) o;
        return (f.getEstado() == this.estado && f.getGolosVisitado() == this.golosVisitado && f.getGolosVisitante() == this.golosVisitante);
    }
    public Futebol clone(){
        return new Futebol(this);
    }
    public String toString(){
        return "Estado: " + this.estado + "\nGolos Visitado: " + this.golosVisitado + "\nGolos Visitante: " + this.golosVisitante;
    }
    public void startGame(){
        if(getEstado() == 0)setEstado(1);
    }
    public void endGame(){
        if(getEstado() == 1)setEstado(2);
    }
    public void goloVisitado(){
        setGolosVisitado(getGolosVisitado() + 1);
    }
    public void goloVisitante(){
        setGolosVisitante(getGolosVisitante() + 1);
    }
    //public String resultadoActual() é igual ao método toString()
}
