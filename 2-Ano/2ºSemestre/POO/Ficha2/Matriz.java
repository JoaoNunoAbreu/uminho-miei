import java.util.Arrays;
/**
 * Escreva a descrição da classe Matriz aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Matriz{
    private int [][] matrix;
    public Matriz(int [][] a){
        this.matrix = a;
    }
    // (a)
    public String toString(){
        String str = new String();
        for(int i = 0; i < matrix.length; i++){
            str = str + Arrays.toString(matrix[i]);
            str = str + "\n";
        }    
        return str;
    }
    // (b)
    public Matriz somaMatriz(Matriz matrix2){
        for(int i = 0; i < matrix.length;i++)
            for(int j = 0; j < matrix.length;j++)
                matrix2.matrix[i][j] += matrix[i][j];
        return matrix2;        
    }
    // (c)
    public boolean isEqual(Matriz matrix2){
        boolean r = false;
        for(int i = 0; i < matrix.length;i++)
            for(int j = 0; j < matrix.length;j++)
                if(matrix[i][j] != matrix2.matrix[i][j]) r = true;
        return !r;        
    }
    // (d)
    public Matriz oposta(){
        Matriz res = new Matriz(matrix);
        for(int i = 0; i < matrix.length;i++)
            for(int j = 0; j < matrix.length;j++)
                res.matrix[i][j] = -matrix[i][j];
        return res;
    }
}
