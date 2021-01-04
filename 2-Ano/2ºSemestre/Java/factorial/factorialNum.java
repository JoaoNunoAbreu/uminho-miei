import java.util.Scanner;


public class factorialNum {

    public static int factorial(int x) {

        int sum = 1;

        for(;x > 0; x--) sum = x * sum;
        return (sum);

    }

    public static void main(String[] args) {

        int b;
        //int a;
        String a;

        //Scanner reader = new Scanner(System.in);
        System.out.println("Insira um número:");
        //a = reader.nextInt();
        a = System.console().readLine();
        b = factorial(Integer.parseInt(a));
        System.out.println("O fatorial de " + a + " é " + b);
    }
}