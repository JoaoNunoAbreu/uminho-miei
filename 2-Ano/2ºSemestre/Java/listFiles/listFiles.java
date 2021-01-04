import java.io.*; 

public class listFiles {

    public static void main(String[] args){

        File folder = new File("/Users/joaonunoabreu/Desktop/2º Ano/Java/listFiles");
        File[] listOfFiles = folder.listFiles();
        
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile())
                System.out.println("File " + listOfFiles[i].getName());
            else 
                if (listOfFiles[i].isDirectory()) {
                    System.out.println("Directory " + listOfFiles[i].getName());
          }
        }

    }

}