import java.lang.Math;
/**
 * Escreva a descrição da classe Ficha2 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Ficha2{ 
    public int min(int[] valores){
        int r = valores[0];
        for(int i = 1; i < valores.length; i++)
            if (valores[i] < r) 
                r = valores[i]; 
        return r;
    }
    public int[] paraArray(int [] valores, int i, int f){
        int [] r = new int[f-i+1];
        System.arraycopy(valores,i,r,0,f-i+1);
        return r;
    }
    private boolean elem(int [] a, int x){
        boolean r = false;
        for(int i = 0; i < a.length && r == false;i++)
            if (a[i] == x) r = true;
        return r;
    }
    public int[] comuns(int [] a, int [] b){
        int count = 0;
        int []r = new int[Math.min(a.length,b.length)];
        
        for (int i = 0; i < a.length; i++)
            for(int j = 0; j < b.length;j++)
                if (a[i] == b[j] && elem(r,a[i]) == false)
                    r[count++] = a[i];
        int [] res = new int[count];
        System.arraycopy(r,0,res,0,count);
        return res;        
    }
}
