import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A classe de teste Testes.
 *
 * @author  (seu nome)
 * @version (um número de versão ou data)
 */
public class Testes
{
    /**
     * Construtor default para a classe de teste Testes
     */
    public Testes()
    {
    }
    private Parque p;
    /**
     * Define a .
     *
     * Chamado antes de cada método de caso de teste.
     */
    @Before
    public void setUp(){
        Map<String,Lugar> lugares = new HashMap<>();
        
        Lugar l1 = new Lugar("10 - AB - 10","Teste1",10,true);
        Lugar l2 = new Lugar("20 - HV - 20","Teste2",20,false);
        Lugar l3 = new Lugar("30 - KT - 30","Teste3",1, true);
        
        lugares.put("aaa",l1);
        lugares.put("bbb",l2);
        lugares.put("ccc",l3);
        p = new Parque("nome",lugares);
    }
    @Test
    public void testeKeySet(){
        Set<String> ms = p.matriculas();
        ms.clear();
        assertEquals(p.matriculas().size(),3);
    }
    @Test
    public void testeSize(){
        assertEquals(p.numlugares(),3);
    }
    /**
     * Tears down the test fixture.
     *
     * Chamado após cada método de teste de caso.
     */
    @After
    public void tearDown()
    {
    }
}
