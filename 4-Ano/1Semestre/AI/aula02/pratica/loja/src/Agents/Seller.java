package Agents;

import Behaviours.LucroBehaviour;
import Behaviours.RecebePedidosBehaviour;
import jade.core.Agent;
import java.util.HashMap;
import java.util.Map;

public class Seller extends Agent {

    private final Map<String, Integer> produto_preco = new HashMap<>();
    private final Map<String, Integer> produto_vendas = new HashMap<>();

    protected void setup(){
        super.setup();
        produto_preco.put("A",1); produto_vendas.put("A",0);
        produto_preco.put("B",2); produto_vendas.put("B",0);
        produto_preco.put("C",3); produto_vendas.put("C",0);
        produto_preco.put("D",4); produto_vendas.put("D",0);
        produto_preco.put("E",5); produto_vendas.put("E",0);
        produto_preco.put("F",6); produto_vendas.put("F",0);
        System.out.println(produto_preco.toString());
        addBehaviour(new RecebePedidosBehaviour(produto_preco,produto_vendas));
        addBehaviour(new LucroBehaviour(this,10000,produto_preco,produto_vendas));
    }

    protected void takeDown(){
        super.takeDown();
        System.out.println(this.getLocalName() + " a morrer...");
    }
}
