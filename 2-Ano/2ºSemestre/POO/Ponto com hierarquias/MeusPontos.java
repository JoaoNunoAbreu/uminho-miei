import java.util.List;
import java.util.ArrayList;
/**
 * Escreva a descrição da classe MeusPontos aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class MeusPontos
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private List<Ponto> pontos;

    /**
     * COnstrutor para objetos da classe MeusPontos
     */
    public MeusPontos()
    {
        // inicializa variáveis de instância
        this.pontos = new ArrayList<>();
    }
    public void adicionaPonto(Ponto p){
        this.pontos.add(p.clone());
    }
    /**
     * Calcula a soma das coordenadas x.
     */
    public int soma(){
        int sum = 0;
        for(Ponto p: pontos){
            sum += p.getX();
        }
        return sum;
    }
}
