import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Collection;
/**
 * Write a description of class Parque here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Parque{
    private String nome;
    private Map<String,Lugar> lugares;
    
    public Parque(){
        this.nome = "N/A";
        this.lugares = new HashMap<String,Lugar>();
    }
    public Parque(String nome, Map<String,Lugar> lugares){
        this.nome = nome;
        setLugares(lugares);
    }
    public Parque(Parque outro){
        this.nome = outro.getNome();
        this.lugares = outro.getLugares();
    }
    public String getNome(){
        return this.nome;
    }
    public Map<String,Lugar> getLugares(){
        Map<String,Lugar> res = new HashMap<>();
        for(String s : this.lugares.keySet()){
            Lugar l = lugares.get(s);
            res.put(s,l);
        }
        return res;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setLugares(Map<String,Lugar> arg){
        Map<String,Lugar> res = new HashMap<String,Lugar>();
        for (Map.Entry<String,Lugar> entry : arg.entrySet()){
            res.put(entry.getKey(),entry.getValue());
        }
        this.lugares = res;
    }
    @Override
    public String toString(){
        return "Nome: " + this.nome + "\n" + 
               "Lugares: " + this.lugares.toString();
            }
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        
        Parque p = (Parque) o;
        return this.nome.equals(p.getNome()) && this.lugares.equals(p.getLugares());
    }
    @Override
    public Parque clone(){
        return new Parque(this);
    }
    // (a)
    public Set<String> matriculas(){
        Set<String> res = new HashSet<String>();
        for(String s : this.lugares.keySet())
            res.add(s);
        return res;
    }
    // (b)
    public void addLugar(String str, Lugar l){
        this.lugares.put(str,l);
    }
    // (c)
    public void removeLugar(String str){
        this.lugares.remove(str);
    }
    // (d)
    public void alteraTempo(String str, int minutos){
        this.lugares.get(str).setMinutos(minutos);
    }
    // (e)
    public int tempoTotalInterno(){
        int count = 0;
        for(Lugar l : this.lugares.values())
            count += l.getMinutos();
        return count;
    }
    public int tempoTotalExterno(){
        int count = 0;
        Iterator<Lugar> it = this.lugares.values().iterator();
        while(it.hasNext()){
            Lugar l = it.next();
            count += l.getMinutos();
        }
        return count;
    }
    // (f)
    public boolean containsMatricula(String matricula){
        return this.lugares.containsKey(matricula);
    }
    // (g)
    public List<String> matriculasComTempoMaiorQueInterno(int x){
        List<String> res = new ArrayList<String>();
        for(Lugar l: this.lugares.values())
            if(l.getPermanente() && l.getMinutos() > x)
                res.add(l.getMatricula());
        return res;
    }
    public List<String> matriculasComTempoMaiorQueExterno(int x){
        List<String> res = new ArrayList<String>();
        Iterator<Lugar> it = this.lugares.values().iterator();
        while(it.hasNext()){
            Lugar l = it.next();
            if(l.getPermanente() && l.getMinutos() > x)
                res.add(l.getMatricula());
        }
        return res;
    }
    // (h)
    public Collection<Lugar> copiaDosLugares(){
        return this.lugares.values();
    }
    // (i)
    public Lugar getLugar(String matricula){
        return this.lugares.get(matricula);
    }
}
