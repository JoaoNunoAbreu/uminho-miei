import java.util.Scanner;
import java.util.Arrays;
/**
 * Escreva a descrição da classe testePrograma6 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class testePrograma6{
    public static void main(String [] args){
       Scanner sc = new Scanner(System.in);
       System.out.println("De que tamanho vai ser a matriz?");
       int size = sc.nextInt();
       
       int [][] tabela = new int[size][size];
       int [][] tabela2 = new int[size][size];

       for(int i = 0; i < size; i++)
           for(int j = 0; j < size; j++){
           System.out.println("Insira o elemento " + i + " " + j + " da matriz 1");
           tabela[i][j]= sc.nextInt();
           System.out.println("Insira o elemento " + i + " " + j + " da matriz 2");
           tabela2[i][j]= sc.nextInt();
       }
       
       // Criação do Objeto
       Matriz m  = new Matriz(tabela);
       Matriz m2 = new Matriz(tabela2);
       
       // (a)
       System.out.println(m.toString()); // print da matriz 1
       System.out.println(m2.toString()); // print da matriz 2
       
       // (b)
       //m.somaMatriz(m2);
       //System.out.println("A soma das matrizes é\n " + m2.toString());
       
       // (c)
       //if (m.isEqual(m2)) System.out.println("As matrizes são iguais.");
       //else System.out.println("As matrizes não são iguais.");
       
       // (d)
       m.oposta();
       System.out.println("A matriz oposta é \n" + m.toString());
    }
}
