import java.util.Scanner;
/**
 * Ficheiro com a função main que testa os métodos.
 * 
 * @author (João Nuno Abreu) 
 * @version (12/02)
 */

public class TestePrograma 
{
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        Ficha1 f = new Ficha1();
        
        /* Exercício 1
        System.out.println("Insira uma temperatura");
        double temperatura = sc.nextDouble();
        System.out.println(f.celsiusParaFarenheit(temperatura));
        */
       
        /* Exercício 2
        System.out.println("Insira 2 números");
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("O maior número é: " + f.maximoNumeros(a,b));
        */
        
        /* Exercício 3
        System.out.println("Insira um nome:");
        String nome = sc.next();
        System.out.println("Insira um saldo:");
        double saldo = sc.nextDouble();
        System.out.println(f.criaDescricaoConta(nome,saldo));
        */
       
        /* Exercício 4
        System.out.println("Insira um valor");
        double valor = sc.nextDouble();
        System.out.println("Insira uma taxa de conversão");
        double taxa = sc.nextDouble();
        System.out.println(f.eurosParaLibras(valor,taxa));
        */
       
        /* Exercício 5
        System.out.println("Insira um inteiro");
        int a = sc.nextInt();
        System.out.println("Insira outro inteiro");
        int b = sc.nextInt();
        double c = f.ordemMedia(a,b);
        System.out.println("A média é " + c);
        */
       
        /* Exercício 6
        int a = Integer.parseInt(args[0]);
        System.out.println(f.factorial(a));
        */
        
        // Exercício 7
        long a = System.nanoTime();
        f.factorial(5000);
        long b = System.nanoTime();
        System.out.println("O tempo gasto para calcular o fatorial de 5000 é de " + f.tempoGasto(a,b)+ " ms");
    }
}
