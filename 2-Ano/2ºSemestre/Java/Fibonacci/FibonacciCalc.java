import java.util.Scanner;

public class FibonacciCalc{

    public static void fibonacci(int count) {

        int atual,ant,sum;

        atual = 1;
        ant = 0;

        System.out.print("0 ");
        System.out.print("1 ");

        for(int i = 0; i < count; i++) {
            sum = atual + ant;
            ant = atual;
            System.out.print(sum + " ");
            atual = sum;
            
        }
        System.out.println("");

    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Insira um número: ");
        int a = reader.nextInt();
        fibonacci(a-2);
    }


}