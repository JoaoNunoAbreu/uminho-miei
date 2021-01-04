import java.util.Scanner;
import java.util.Arrays;
/**
 * Escreva a descrição da classe testePrograma2 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class testePrograma2
{
    public static void main(String[] args){
       Scanner sc = new Scanner(System.in);
       int [][] notasTurma = new int[3][3];
        
       // (a)
       for(int i = 0; i < 3; i++)
           for(int j = 0; j < 3; j++){
           System.out.println("Insira o elemento " + i + " " + j + " da matriz");
           notasTurma[i][j]= sc.nextInt();
       }
       for(int i = 0; i < 3; i++) System.out.println(Arrays.toString(notasTurma[i]));
        
       Pauta p = new Pauta(notasTurma); // Criação do objeto Pauta
        
       // (b)
       /*System.out.println("Escolha uma UC");
       int x = sc.nextInt();
       System.out.println("A soma das notas da UC " + x + " é " + p.somaUC(x));*/
        
       // (c)
       /*System.out.println("Escolha um aluno");
       int x = sc.nextInt();
       System.out.println("A média das notas de um aluno " + x + " é " + p.mediaAluno(x));*/
       
       // (d)
       /*System.out.println("Escolha uma UC");
       int x = sc.nextInt();
       System.out.println("A média das notas da UC " + x + " é " + p.mediaUC(x));*/
       
       //(e)
       //System.out.println("A nota mais alta é : " + p.maximaNota());
       
       // (f)
       //System.out.println("A nota mais baixa é : " + p.minimaNota());
       
       // (g)
       /*System.out.println("Selecione uma nota");
       int x = sc.nextInt();
       System.out.println("A lista de notas superiores a " + x + " são " + Arrays.toString(p.notasSup(x)));*/
       
       // (h)
       //System.out.println("As notas são : " + p.stringNotas());
       
       // (i)
       System.out.println("A UC com média mais alta é a número " + p.bestUC());
    }
}
