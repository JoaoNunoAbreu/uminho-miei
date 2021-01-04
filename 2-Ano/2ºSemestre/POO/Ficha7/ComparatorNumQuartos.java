import java.util.Comparator;
public class ComparatorNumQuartos implements Comparator<Hotel>{
    public int compare(Hotel h1, Hotel h2){
        int n1 = h1.getQuartosDisponiveis();
        int n2 = h2.getQuartosDisponiveis();
        int r = 0;
        if(n1 > n2) r = -1;
        else if (n1 < n2) r = 0;
        return r;
    }
}
