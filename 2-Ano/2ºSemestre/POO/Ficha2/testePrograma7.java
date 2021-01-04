import java.lang.Math;
import java.util.Arrays;
import java.util.Scanner;
/**
 * Escreva a descrição da classe testePrograma7 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class testePrograma7{
    public static void main(String [] args){
        int max = 50;
        int min = 1;
        int max2 = 9;
        int min2 = 1;
        int [] chave = new int [5];
        int [] estrelas = new int [2];
        
        // import da classe chavesClass
        chavesClass c = new chavesClass();
        
        for(int i = 0; i < chave.length; i++) {
            int value = (int)(Math.random() * ((max - min) + 1)) + min;
            // Para não haver chaves repetidas
            while(c.elem(chave,value,i))
                value = (int)(Math.random() * ((max - min) + 1)) + min;
            chave[i] = value;    
        }
        for(int i = 0; i < estrelas.length; i++){
            int value2 =(int)(Math.random() * ((max2 - min2) + 1)) + min2;
            // Para não haver estrelas repetidas
            while(c.elem(estrelas,value2,i))
                value2 = (int)(Math.random() * ((max2 - min2) + 1)) + min2;
            estrelas[i] = value2;
        }
        Arrays.sort(chave);   // Ordena as chaves
        Arrays.sort(estrelas);// Ordena as estrelas
        System.out.println("As chaves geradas foram : " + Arrays.toString(chave));
        System.out.println("As estrelas geradas foram : " + Arrays.toString(estrelas));
        
        // Parte do input
        Scanner sc = new Scanner(System.in);
        
        int [] chavesInput = new int[5];
        for(int i = 0; i < 5; i++){
            System.out.println("Insira o número " + (i+1) + "/5");
            chavesInput[i] = sc.nextInt();
        }
        int [] estrelasInput = new int [2];
        for(int i = 0; i < 2; i++){
            System.out.println("Insira a estrela " + (i+1) + "/2");
            estrelasInput[i] = sc.nextInt();
        }

        if(c.isEqual(chave,chavesInput) && c.isEqual(estrelas,estrelasInput))
            c.vitoria(chavesInput,estrelasInput);
        else {
            System.out.print("As chaves em comum foram : " + Arrays.toString(c.comuns(chave,chavesInput)) + "\n");
            System.out.print("As estrelas em comum foram : " + Arrays.toString(c.comuns(estrelas,estrelasInput)));
        }    
    }
}
