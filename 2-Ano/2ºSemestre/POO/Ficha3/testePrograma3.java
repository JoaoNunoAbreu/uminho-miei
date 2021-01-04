import java.util.Arrays;
import java.time.LocalDate;
/**
 * Escreva a descrição da classe testePrograma3 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class testePrograma3{
    public static void main (String [] args){
        Video v = new Video();
        
        byte[] bytes = new byte[100];
        bytes[0] = 1;
        
        String [] str = new String[100];
        str[0] = "Uma merda";
        str[1] = "xD";
        Video x = new Video("N/A",bytes,LocalDate.now(),720,120,str,100,10);
        Video v2 = new Video();
        System.out.println(v.equals(v2));
        //System.out.println(v.toString());
        //System.out.println(x.processa());
    }
}
