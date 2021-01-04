import java.util.Scanner;
import java.util.Arrays;
/**
 * Escreva a descrição da classe testePrograma5 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class testePrograma5{
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        Palavras p = new Palavras();
        
        System.out.println("De que tamanho vai ser o array?");
        int size = Integer.parseInt(sc.nextLine());
        String [] a = new String[size];
        
        for(int i = 0; i < size; i++){
            System.out.println("Insira o elemento " + i + " da lista");
            a[i] = sc.nextLine();
        }
        // (a)
        //a = p.noRep(a);
        System.out.println("O array inserido foi " + Arrays.toString(a));
        
        // (b)
        //System.out.println("A maior string é : " + p.maiorString(a));
        
        // (c)
        System.out.println("Os elementos da lista " + Arrays.toString(p.noRep(p.repetidos(a))) + " repetem-se");
        
        // (d)
        /*System.out.println("Insira uma string para contar quantas vezes aparece no array");
        String str = sc.nextLine();
        System.out.println("A string " + str + " ocorre " + p.countStr(a,str) + " vezes no array");*/
    }
}
