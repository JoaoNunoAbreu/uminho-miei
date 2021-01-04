import java.util.Scanner;

public class numberToWords{

    public static void converter(int num) {

        String string = new String();

        if(num >= 100 && num < 200)  {string = "One hundred "  ;num = num - 100;}
        if(num >= 200 && num < 300)  {string = "Two hundred "  ;num = num - 200;}
        if(num >= 300 && num < 400)  {string = "Three hundred ";num = num - 300;}
        if(num >= 400 && num < 500)  {string = "Four hundred " ;num = num - 400;}
        if(num >= 500 && num < 600)  {string = "Five hundred " ;num = num - 500;}
        if(num >= 600 && num < 700)  {string = "Six hundred "  ;num = num - 600;}
        if(num >= 700 && num < 800)  {string = "Seven hundred ";num = num - 700;}
        if(num >= 800 && num < 900)  {string = "Eight hundred ";num = num - 800;}
        if(num >= 900 && num < 1000) {string = "Nine hundred " ;num = num - 900;}

        if(string != null && !string.isEmpty()) string = string + "and ";

        if(num >= 10 && num < 20)  {
            if (num == 11) {string = string + "eleven ";   num = num - 11;}
            if (num == 12) {string = string + "twelve ";   num = num - 12;}
            if (num == 13) {string = string + "thirteen "; num = num - 13;}
            if (num == 14) {string = string + "fourteen "; num = num - 14;}
            if (num == 15) {string = string + "fifteen ";  num = num - 15;}
            if (num == 16) {string = string + "sixteen ";  num = num - 16;}
            if (num == 17) {string = string + "seventeen ";num = num - 17;}
            if (num == 18) {string = string + "eighteen "; num = num - 18;}
            if (num == 19) {string = string + "nineteen "; num = num - 19;}
        }            
        if(num >= 20 && num < 30)  {string = string + "twenty " ;num = num - 20;}
        if(num >= 30 && num < 40)  {string = string + "thirty " ;num = num - 30;}
        if(num >= 40 && num < 50)  {string = string + "fourty " ;num = num - 40;}
        if(num >= 50 && num < 60)  {string = string + "fifty "  ;num = num - 50;}
        if(num >= 60 && num < 70)  {string = string + "sixty "  ;num = num - 60;}
        if(num >= 70 && num < 80)  {string = string + "seventy ";num = num - 70;}
        if(num >= 80 && num < 90)  {string = string + "eighty " ;num = num - 80;}
        if(num >= 90 && num < 100) {string = string + "ninety " ;num = num - 90;}

        if(num == 1) string = string + "one ";
        if(num == 2) string = string + "two ";
        if(num == 3) string = string + "three ";
        if(num == 4) string = string + "four ";
        if(num == 5) string = string + "five ";
        if(num == 6) string = string + "six ";
        if(num == 7) string = string + "seven ";
        if(num == 8) string = string + "eight ";
        if(num == 9) string = string + "nine ";

        System.out.println(string);
    }

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        int a, flag = 0;
        char c;

        while (flag == 0){
            System.out.println("Insira um número: ");
            a = reader.nextInt();
            converter(a);
            System.out.println("Número convertido com sucesso. Deseja continuar? (y/n) ");
            c = reader.next().charAt(0); 
            if (c != 'y') flag = 1;
        }
    }

}