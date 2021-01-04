import java.io.*; 

public class fileReader { 

    public static void main(String[] args)throws Exception{ 

        File file = new File("Braga_Amarante.txt"); 
    
        BufferedReader br = new BufferedReader(new FileReader(file)); 
    
        String str;
        while (true) {
            str = br.readLine();
            if(str == null) break;
            System.out.println(str); 
        }
    } 
}