import java.util.Comparator;
public class ComparatorKms implements Comparator<Cliente>{
    public int compare(Cliente c1, Cliente c2){
        double n1 = c1.kmsPercorridos();
        double n2 = c2.kmsPercorridos();
        int r = 0;
        if(n1 > n2) r = -1;
        else if (n1 < n2) r = 1;
        return r;
    }
}
