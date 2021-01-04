import java.util.Arrays;
/**
 * Escreva a descrição da classe ex5 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Palavras {
    private boolean elem(String [] palavras, String key, int size){
        boolean r = false;
        for(int i = 0;i < size && r == false;i++)
            if(key.equals(palavras[i])) r = true;
        return r;
    }
    public String[] noRep(String [] palavras){
        int j = 0;
        String []res = new String[palavras.length];
        for(int i = 0; i < palavras.length;i++)
            if(!elem(palavras,palavras[i],i)) res[j++] = palavras[i];
        String [] r = new String[j];    
        System.arraycopy(res,0,r,0,j);
        return r;
    }
    public String maiorString (String [] palavras){
        int max = 0;
        String r = null;
        for(int i = 0; i < palavras.length; i++){
            if (palavras[i].length() > max) {
                max = palavras[i].length();
                r = palavras[i];
            }
        }
        return r;
    }
    public String[] repetidos(String [] palavras){
        int j = 0;
        String [] res = new String[palavras.length];
        for(int i = 0; i < palavras.length;i++)
            if(elem(palavras,palavras[i],i)) res[j++] = palavras[i];
        String [] r = new String[j];
        System.arraycopy(res,0,r,0,j);
        return r;
    }
    public int countStr(String [] palavras, String str){
        int count = 0;
        for(int i = 0; i < palavras.length;i++)
            if(str.equals(palavras[i])) count++;
        return count;
    }
}
