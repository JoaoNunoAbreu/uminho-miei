import java.util.Comparator;
public class ComparatorNumVezes implements Comparator<Cliente>{
    public int compare(Cliente c1, Cliente c2){
        int n1 = c1.getHist().size();
        int n2 = c2.getHist().size();
        int r = 0;
        if(n1 > n2) r = -1;
        else if (n1 < n2) r = 1;
        return r;
    }
}
