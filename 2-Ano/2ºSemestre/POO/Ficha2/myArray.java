import java.util.Arrays;
/**
 * Escreva a descrição da classe Exercicio4 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class myArray{
    private int[] a;
    public myArray(int [] array){
        this.a = array;
    }
    public String toString(){
        return Arrays.toString(a);
    }
    public void mySort(){
        Arrays.sort(a);
    }
    public int binarySearch(int key){
        return Arrays.binarySearch(a,key);
    }
}
