import java.util.Scanner;

public class allPrimes{

    public static void primes(int size) {

        int count = 0;
        int flag;

        for(int i = 2; i < size; i++) {

            flag = 0;
            for(int j = 2; j < i-1 && flag == 0; j++)
                if(i%j == 0) flag = 1;

            if(flag == 0) {System.out.println("O número " + i + " é primo."); count++;}
        }
        System.out.println("Entre 0 e " + size + " existem " + count + " números primos.");
    }


    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        System.out.println("Insira um número: ");
        int a = reader.nextInt();
        primes(a);

    }

}