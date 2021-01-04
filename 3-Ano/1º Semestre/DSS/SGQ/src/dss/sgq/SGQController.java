/*
 * DISCLAIMER: Este código foi criado para discussão e edição durante as aulas 
 * práticas de DSS, representando uma solução em construção. Como tal, não deverá 
 * ser visto como uma solução canónica, ou mesmo acabada. É disponibilizado para 
 * auxiliar o processo de estudo. Os alunos são encorajados a testar adequadamente 
 * o código fornecido e a procurar soluções alternativas, à medida que forem 
 * adquirindo mais conhecimentos.
 */
package dss.sgq;
import dss.pubsub.DSSObservable;
import dss.pubsub.DSSObserver;
import java.util.Map;
import java.util.TreeMap;

public class SGQController extends DSSObservable implements DSSObserver {
    
    private double screen_value;          // o valor que está a ser lido
    private Map<Integer,Aluno> alunos;    // alunos guardados por número
    
    /** Creates a new instance of SGQ */
    public SGQController(SGQModel model) {
        this.alunos = new TreeMap<>();
    }
    public Map<Integer, Aluno> getAlunos(){
        Map<Integer, Aluno> r = new TreeMap<>();
        for(Map.Entry<Integer,Aluno> entry : this.alunos.entrySet())
            r.put(entry.getKey(),entry.getValue());
        return r;
    }
    
    /*public void pagarQuota(Integer numero, Double valor){
        
    }*/
    
    public Aluno getAluno(int num){
        return this.alunos.get(num);
    }
    
    public void addAluno(Aluno a){
        this.alunos.put(a.getNumero(),a);
    }
    
    @Override
    public void update(DSSObservable source, Object value) {
        this.screen_value = Double.parseDouble(value.toString());
    }
}
