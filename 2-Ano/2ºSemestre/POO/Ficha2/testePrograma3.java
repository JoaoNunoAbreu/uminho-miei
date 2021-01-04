import java.time.LocalDate;
import java.util.Arrays;
/**
 * Escreva a descrição da classe testePrograma3 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class testePrograma3
{
    public static void main(String [] args){
        LocalDate [] array = new LocalDate[3];
        Calendario c = new Calendario(array);
        
        c.insereData(LocalDate.now());
        c.insereData(LocalDate.of(1999,6,24));
        c.insereData(LocalDate.ofYearDay(2010, 65));
        System.out.println(c.toString());
        
        LocalDate date = LocalDate.of(2015,3,3);
        System.out.println("A data mais próxima de " + date + " é " + c.dataMaisProxima(date));
    }
}
