import java.time.LocalDate;
/**
 * Escreva a descrição da classe Lampada aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Lampada{
    private int estado;
    private int consumo;
    private LocalDate timestamp;
    public Lampada(){
        this.estado = 0;
        this.consumo = 0;
    }
    public void lampON(){
        this.estado = 1;
        this.consumo = 5;
        this.timestamp = LocalDate.now();
    }
    public void lampOFF(){
        this.estado = 0;
        this.consumo = 0;
        this.timestamp = LocalDate.now();
    }
    public void lampECO(){
        this.estado = 2;
        this.consumo = 2;
    }
    //public double totalConsumo(){}
}
