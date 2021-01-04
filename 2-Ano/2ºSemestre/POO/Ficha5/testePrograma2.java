import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
/**
 * Escreva a descrição da classe testePrograma2 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class testePrograma2
{
    public static void main(String [] args){
        LinhaEncomenda le1 = new LinhaEncomenda("ABC","Descrição1",12.3,50,2.3,0.2);
        LinhaEncomenda le2 = new LinhaEncomenda("CDE","Descrição2",66.6,10,2.3,0.2);
        LinhaEncomenda le3 = new LinhaEncomenda("FGH","Descrição3",53.4,20,2.3,0.2);
        LinhaEncomenda le4 = new LinhaEncomenda("NEW","Descrição4",53.4,20,2.3,0.2);
        List<LinhaEncomenda> lista = new ArrayList<>(3);
        
        lista.add(le1);
        lista.add(le2);
        lista.add(le3);
        
        Encomenda e = new Encomenda("Encomenda 1", 12,"Moro aqui",20,LocalDate.now(),lista);
        System.out.println(e.toString());
        System.out.println("O valor total é: " + e.calculaValorTotal());
        System.out.println("O valor desconto total é: " + e.calculaValorDesconto());
        System.out.println("O número total de produtos é: " + e.numeroTotalProdutos());
        System.out.println(e.existeProdutoEncomenda("ASDASDASD"));
        System.out.println("-------------------------");
        e.adicionaLinha(le4);
        System.out.println(e.toString());
        System.out.println("-------------------------");
        e.removeProduto("ABC");
        System.out.println(e.toString());
    }
}
