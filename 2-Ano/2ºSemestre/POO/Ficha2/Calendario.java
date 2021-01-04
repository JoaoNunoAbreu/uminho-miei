import java.time.LocalDate;
import java.util.Arrays;
import java.time.temporal.ChronoUnit;
/**
 * Escreva a descrição da classe ex3 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Calendario {
    private LocalDate [] array;
    public Calendario(LocalDate [] array){
        this.array = array;
    }
    public void insereData(LocalDate data){
        for(int i = 0; i < array.length; i++){
            if (array[i] == null){array[i] = data;break;}
        }
    }
    public LocalDate dataMaisProxima(LocalDate data){
        long min = ChronoUnit.DAYS.between(array[0],data);
        LocalDate res = array[0];
        for(int i = 1; i < array.length;i++)
            if(ChronoUnit.DAYS.between(array[i],data) < min){
                min = ChronoUnit.DAYS.between(array[i],data);
                res = array[i];
            }
        return res;     
    }
    public String toString(){
        return Arrays.toString(array);
    }
}
