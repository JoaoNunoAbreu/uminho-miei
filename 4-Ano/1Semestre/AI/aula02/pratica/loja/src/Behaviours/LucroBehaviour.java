package Behaviours;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

import java.util.Map;

public class LucroBehaviour extends TickerBehaviour {

    private final Map<String, Integer> produto_preco;
    private final Map<String, Integer> produto_vendas;

    public LucroBehaviour(Agent a, long period, Map<String, Integer> produto_preco, Map<String, Integer> produto_vendas) {
        super(a, period);
        this.produto_preco = produto_preco;
        this.produto_vendas = produto_vendas;
    }

    @Override
    protected void onTick() {
        System.out.println("Lucro total = " + produto_preco.get("A") * produto_vendas.get("A") +
                produto_preco.get("B") * produto_vendas.get("B") +
                produto_preco.get("C") * produto_vendas.get("C") +
                produto_preco.get("D") * produto_vendas.get("D"));
    }
}
