import java.util.Map;
import java.util.HashMap;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.TreeSet;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
/**
 * Escreva a descrição da classe GestaoEncomendas aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class GestaoEncomendas{
    private Map<Integer,Encomenda> encomendas;
    public GestaoEncomendas(){
        this.encomendas = new HashMap<>();
    }
    
    /*public Map<Integer,Encomenda> getEncomendas(){
        return this.encomendas.values.stream().collect(Collectors.toMap((e) -> e.getNEnc(),(
    }*/
    
    // (a)
    public Set<Integer> todosCodigosEnc(){
        return new TreeSet<Integer>(this.encomendas.keySet());
    }
    // (b)
    /*public void addEncomenda(Encomenda enc){
        
    }*/
    // (c)
    // (d)
    // (e)
    public Integer encomendaComMaisProdutos(){
        TreeSet<Encomenda> aux = 
            new TreeSet<>((e1,e2) ->(e1.numProdutos()) - (e2.numProdutos()));
        for(Encomenda e : this.encomendas.values())
            aux.add(e);
        return (aux.last().getNEnc());
    }
    // (f)
    public Set<Integer> encomendasComProduto(String codProd){
        return this.encomendas.values().stream()
                                       .filter(e->e.existeNaEncomenda(codProd))
                                       .map(Encomenda::getNEnc)
                                       .collect(Collectors.toCollection(TreeSet::new)); // Faz com que os dados fiquem ordenados
    }
    // (g)
    public Set<Integer> encomendasAposData(LocalDate d){
        return this.encomendas.values().stream()
                                       .filter(e->e.getData().isAfter(d))
                                       .map(Encomenda::getNEnc)
                                       .collect(Collectors.toSet());
    }
    // (h)
    public Set<Encomenda> encomendasValorDecrescente(){
        TreeSet<Encomenda> aux = 
        new TreeSet<Encomenda>((e1,e2) -> (int)(e2.calculaValorTotal() - e1.calculaValorTotal()));
        for(Encomenda e: this.encomendas.values())
            aux.add(e.clone());
        return aux;
    }
    // (i)
    public Map<String,List<Integer>> encomendasDeProduto(){
        Map<String,List<Integer>> aux = new HashMap<>();
        
        for(Encomenda e: this.encomendas.values()){
            List<String> lprods = e.getListaProdutos();
            for(String codProd : lprods){
                if(!aux.containsKey(codProd))
                    aux.put(codProd,new ArrayList<Integer>());
                List<Integer> ls = aux.get(codProd);
                ls.add(e.getNEnc());
            }
        }
        return aux;    
    }
}

