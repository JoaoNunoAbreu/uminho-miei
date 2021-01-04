import java.util.ArrayList;
import java.time.LocalDateTime;
/**
 * Escreva a descrição da classe testePrograma4 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class testeGestao
{
    public static void main(String [] args){
        ArrayList<String> comments = new ArrayList<>();
        comments.add("Teste1");
        comments.add("Teste2");
        comments.add("Teste3");
        
        FBPost a1 = new FBPost(123,"Joao",LocalDateTime.now(),"Hello World",2,comments);
        FBPost a2 = new FBPost(444,"Joao",LocalDateTime.now(),"Bye World",100,comments);
        FBPost a3 = new FBPost();
        
        
    }
}
