import java.util.Map;
import java.util.HashMap;
public class Main1{
    public static void main(String [] args){
        Lugar l1 = new Lugar("90 - IR - 55","João Nuno Abreu",420,true);
        Lugar l2 = new Lugar("12 - KA - 34","Random", 200, false);
        Lugar l3 = new Lugar("36 - LG - 03","Outro", 10, true);
        
        Map<String,Lugar> lugares = new HashMap<String,Lugar>();
        lugares.put(l1.getMatricula(),l1);
        lugares.put(l2.getMatricula(),l2);
        lugares.put(l3.getMatricula(),l3);
        
        Parque p = new Parque("Parque de Gays",lugares);
        
        // (a)
        //System.out.println(p.matriculas());
        // (b)
        //p.addLugar("56 - OK - 21", new Lugar("56 - OK - 21","idk",4,true));
        // (c)
        //p.removeLugar("90 - IR - 55");
        // (d)
        //p.alteraTempo("90 - IR - 55",99999999);
        // (e)
        //System.out.println(p.tempoTotalInterno());
        //System.out.println(p.tempoTotalExterno());
        // (f)
        //System.out.println(p.containsMatricula("a"));
        //System.out.println(p.containsMatricula("36 - LG - 03"));
        // (g)
        //System.out.println(p.matriculasComTempoMaiorQueInterno(5));
        //System.out.println(p.matriculasComTempoMaiorQueExterno(5));
        // (h)
        //System.out.println(p.copiaDosLugares());
        // (i)
        System.out.println(p.getLugar("90 - IR - 55"));
        //System.out.println(p.toString());
    }
}
