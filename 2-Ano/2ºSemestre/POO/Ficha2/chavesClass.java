import java.util.Arrays;
/**
 * Escreva a descrição da classe chaves aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class chavesClass
{
    public boolean elem(int[] valores, int key, int size){
        boolean r = false;
        for(int i = 0; i < size;i++)
            if(valores[i] == key) r = true;
        return r;    
    }
    public boolean isEqual(int [] a, int [] b){
        boolean r = true;
        for(int i = 0; i < a.length;i++)
            if(!elem(b,a[i],a.length)) r = false;
        return r;    
    }
    public int [] comuns(int [] a, int [] b){
        int [] r = new int[a.length];
        int count = 0;
        for(int i = 0; i < a.length;i++)
            if(elem(b,a[i],a.length)) r[count++] = a[i];
        int [] res = new int[count];
        System.arraycopy(r,0,res,0,count);
        return res;
    }
    public void vitoria(int [] chaves, int [] estrelas){
        String str = new String();
        str = Arrays.toString(chaves) + Arrays.toString(estrelas);
        for(int i = 0; i < 50; i++){
            System.out.println(str);
            str = "  " + str;
        }
    }
}
