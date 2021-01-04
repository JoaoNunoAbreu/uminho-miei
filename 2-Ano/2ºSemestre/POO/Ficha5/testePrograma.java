import java.util.List;
import java.util.ArrayList;
/**
 * Escreva a descrição da classe testePrograma aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class testePrograma{
    public static void main(String [] args){
         ArrayList<String> al = new ArrayList<String>();
         al.add("Hello");
         al.add("Teste");
         Stack s  = new Stack(al);
         Stack s1 = new Stack(); // Stack vazia
         s1.push("E1");
         
         System.out.println("Inicial: " + s.toString());
         System.out.println("O elemento do topo da stack é: " + s.top());
         s.push("Fim");
         System.out.println("Depois de um push: " + s.toString());
         s1.pop();
         System.out.println("Depois de um pop: " + s.toString());
         System.out.println("O tamanho é: " + s.length());
    }
}
