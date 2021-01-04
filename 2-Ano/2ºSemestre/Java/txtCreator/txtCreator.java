import java.io.*; 

public class txtCreator {

    public static void main(String[] args) throws Exception{

        PrintWriter writer = new PrintWriter("teste.txt", "UTF-8");

        writer.println("Hello,");
        writer.println("World!");
        writer.close();
    }

}