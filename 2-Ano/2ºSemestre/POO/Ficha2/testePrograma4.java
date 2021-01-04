import java.util.Arrays;
/**
 * Escreva a descrição da classe testePrograma4 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class testePrograma4{
    public static void main(String []args){
        int [] a = {4,3,2,5,1};
        myArray array = new myArray(a);
        
        array.mySort();
        System.out.println(array.toString());
        System.out.println(array.binarySearch(3));
    }
}
