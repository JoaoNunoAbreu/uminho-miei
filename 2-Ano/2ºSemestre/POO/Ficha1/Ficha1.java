/**
 * Exercícios resolvidos da ficha 1 de POO.
 * 
 * @author (João Nuno Abreu) 
 * @version (12/02)
 */
public class Ficha1{
    
    // Exercício 1
    public double celsiusParaFarenheit(double graus){
        graus = (graus*9/5)+32;
        return graus;
    }
    
    // Exercício 2
    public int maximoNumeros(int a, int b){
        return (Math.max(a,b));
    }
    
    // Exercício 3
    public String criaDescricaoConta(String nome, double saldo){
        return("O nome é " + nome + " e o saldo é " + saldo);
    }
    
    // Exercício 4
    public double eurosParaLibras(double valor, double taxaConversao){
        return (valor*taxaConversao);
    }
    
    // Exercício 5
    public double ordemMedia(int a, int b){
        if(a<b) System.out.println(a + " < " + b);
        else System.out.println(b + " < " + a);
        return ((a+b)/2.0);
    }
    // Exercício 6
    public long factorial(int num){
        long sum = 1;
        while(num > 0){
            sum *= num;
            num--;
        }
        return sum;
    }
    // Exercício 7 
    public long tempoGasto(long a, long b){
        return (b-a)/1000;
    }
}
