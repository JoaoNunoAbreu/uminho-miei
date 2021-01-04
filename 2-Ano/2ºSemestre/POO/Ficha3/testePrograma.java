import java.util.Scanner;
/**
 * Escreva a descrição da classe testePrograma aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class testePrograma{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Insira o valor de x");
        double x = sc.nextDouble();
        System.out.println("Insira o valor de y");
        double y = sc.nextDouble();
        System.out.println("Insira o valor de r");
        double r = sc.nextDouble();
        Circulo c = new Circulo(x,y,r);
        
        System.out.println(c.toString());
        System.out.println("A área é : " + c.calculaArea());
        System.out.println("O perímetro é : " + c.calculaPerimetro());
    }
}
