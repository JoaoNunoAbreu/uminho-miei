import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.StringBuilder;
import java.util.*;

public class Parser {

    public static void main(String[] args) {

        if(args.length != 1)
            System.out.println("Número de argumentos inválidos!");
        else{
            String csvFile = args[0];
            String line;
            String cvsSplitBy = ";";
            boolean flag = false; /* Para não ler a primeira linha do ficheiro */
            Set<String> linhas = new HashSet<>();

            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

                while ((line = br.readLine()) != null) {

                    /* Tirar aspas a mais */
                    line = line.replace("\"", "");

                    /* O sicstus que estou a usar não consegue carregar strings com letras maíusculas acentuadas (MAC) */
                    line = line.replace("Á","A").replace("É","E");


                    if(!flag) flag = true;
                    else {

                        String[] parts = line.split(cvsSplitBy);

                        if(!linhas.contains(line)){

                            linhas.add(line);

                            /* Lista */
                            String[] lista = parts[7].split(",");
                            StringBuilder sb = new StringBuilder();
                            sb.append("[");
                            for (String s : lista) {
                                s=s.replace(" ","");
                                sb.append((Integer.parseInt(s))).append(",");
                            }
                            sb.setLength(sb.length()-1);
                            sb.append("]");
                            parts[7] = sb.toString();
                            /* ---- */

                            for(int i = 0; i < parts.length; i++)
                                if(parts[i].length() == 0) parts[i] = "Null";

                            if(parts.length == 10)
                                System.out.println("paragem(" + parts[0]+","+parts[1]+","+parts[2]+",\""+parts[3]+"\",\""+parts[4]+"\",\""+parts[5]+"\",\""+parts[6]+"\","+parts[7]+","+parts[8]+",\""+parts[9]+"\","+ "\"Null\").");
                            else System.out.println("paragem(" + parts[0]+","+parts[1]+","+parts[2]+",\""+parts[3]+"\",\""+parts[4]+"\",\""+parts[5]+"\",\""+parts[6]+"\","+parts[7]+","+parts[8]+",\""+parts[9]+"\",\""+ parts[10] + "\").");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}