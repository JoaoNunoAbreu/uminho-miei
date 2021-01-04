public class strings {


    public static Boolean findString(String str1, String str2){

        byte on = 0;
        int i,j;

        for(i = 0,j = 0; i < str1.length() && j < str2.length(); i++)
            if (str1.charAt(i) == str2.charAt(j)) j++; 
            else j = 0;
        
        return (j == str2.length());
    }

    //--------------------------------------------------

    public static String spongebob(String str){

        int flag = 0;
        StringBuffer sb = new StringBuffer("");

        for(int i = 0; i < str.length(); i++){
            if(flag == 1 && str.charAt(i) != ' ') sb.append(Character.toString(Character.toUpperCase(str.charAt(i))));
            if(flag == 0 && str.charAt(i) != ' ') sb.append(Character.toString(Character.toLowerCase(str.charAt(i))));
            if (flag == 0) flag = 1;
            else if(flag == 1) flag = 0;
            if(str.charAt(i) == ' ') {sb.append(" ");flag = 0;}
        }

        String res = sb.toString();

        return res;
    }

    //--------------------------------------------------

    public static void main(String [] args){


        System.out.println("Insira um número");
        System.out.println("1 - findString");
        System.out.println("2 - Replacer");
        System.out.println("3 - Spongebob");
        System.out.println("4 - Sair");

        int op = Integer.parseInt(System.console().readLine());

        if(op == 1){

            System.out.println("Insira uma string:");
            String a = System.console().readLine();
            System.out.println("Insira outra string para testar se está na string anterior:");
            String b = System.console().readLine();

            System.out.println(findString(a,b));
        }
        else if(op == 2){

            System.out.println("Insira uma string:");
            String a = System.console().readLine();
            System.out.println("Insira qual substring vai ser substituída");
            String b = System.console().readLine();
            System.out.println("Insira a string que vai ficar no lugar da anterior");
            String c = System.console().readLine();

            a = a.replaceAll(b,c);
            System.out.println(a);

        }
        else if (op == 3){

            System.out.println("Insira uma string:");
            String a = System.console().readLine();
            System.out.println(spongebob(a));
        }
        else if(op == 4) System.exit(0);
        else System.out.println("Número inválido");

    }

}