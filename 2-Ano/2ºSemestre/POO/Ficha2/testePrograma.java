import java.util.Scanner;
import java.util.Arrays;
/**
 * Escreva a descrição da classe testePrograma aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class testePrograma {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Ficha2 f = new Ficha2();
        
        // Exercício 1
        System.out.println("Quantos valores vão ser lidos?");
        int n = sc.nextInt();
        
        int[] valores = new int[n];
        
        for(int i = 0; i < n; i++){
            System.out.println("Insira o elemento " + i + " do array");
            valores[i] = sc.nextInt();
        }
        
        // (a)
        //System.out.println("O menor número inserido é: " + f.min(valores));
        
        // (b)
        /*System.out.println("Índice inicial");
        int inicio = sc.nextInt();
        System.out.println("Índice final");
        int fim = sc.nextInt();
        System.out.println(Arrays.toString(f.paraArray(valores,inicio,fim)));
        */
        // (c)
        System.out.println("Quantos valores vão ser lidos?");
        int n1 = sc.nextInt();
        int[] valores1 = new int[n1];
        
        for(int i = 0; i < n1; i++){
            System.out.println("Insira o elemento " + i + " do array");
            valores1[i] = sc.nextInt();
        }
        System.out.println(Arrays.toString(f.comuns(valores,valores1)));
    }
}
