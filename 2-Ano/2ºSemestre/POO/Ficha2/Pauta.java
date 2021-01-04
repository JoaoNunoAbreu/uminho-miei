import java.lang.Math;
/**
 * Escreva a descrição da classe Pauta aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Pauta
{
    private int[][] notasTurma; // Variável de instância
    
    public Pauta(int [][] notasTurma){
        this.notasTurma = notasTurma;
    }
    public int somaUC(int uc){
        int r = 0;
        for(int i = 0; i < 3; i++) r += notasTurma[i][uc];
        return r;
    }
    public float mediaAluno(int aluno){
        float r = 0;
        for(int i = 0; i < 3; i++) r += notasTurma[aluno][i];
        return r/3;
    }
    public float mediaUC(int uc){
        float r = 0;
        for(int i = 0; i < 3; i++) r += notasTurma[i][uc];
        return r;
    }
    public int maximaNota(){
        int max = 0;
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                if(notasTurma[i][j] > max) max = notasTurma[i][j];   
        return max;     
    }
    public int minimaNota(){
        int min = notasTurma[0][0];
        for(int i = 0; i < 3; i++)
            for(int j = 1; j < 3; j++)
                if(notasTurma[i][j] < min) min = notasTurma[i][j];   
        return min;     
    }
    public int[] notasSup(int x){
        int [] r = new int[9];
        int a = 0;
        int count = 0;
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                if(notasTurma[i][j] > x){
                    count++;
                    r[a++] = notasTurma[i][j];
                }
        int [] res = new int[count];
        System.arraycopy(r,0,res,0,count);
        return res;
    }
    public String stringNotas(){
        String str = new String();
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                str = str + notasTurma[i][j]; 
        return str;    
    }
    public int bestUC(){
        float a = mediaUC(0);
        float b = mediaUC(1);
        float c = mediaUC(2);
        float r = Math.max(Math.max(a,b),c);
        if (r == a) return 0;
        if (r == b) return 1;
        if (r == c) return 2;
        return -1;
    }
}
