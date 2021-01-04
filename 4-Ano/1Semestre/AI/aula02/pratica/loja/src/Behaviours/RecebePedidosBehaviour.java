package Behaviours;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Map;

public class RecebePedidosBehaviour extends CyclicBehaviour {

    private final Map<String, Integer> produto_preco;
    private final Map<String, Integer> produto_vendas;

    public RecebePedidosBehaviour(Map<String, Integer> produto_preco, Map<String, Integer> produto_vendas) {
        this.produto_preco = produto_preco;
        this.produto_vendas = produto_vendas;
    }

    @Override
    public void action() {

        ACLMessage msg = myAgent.receive();
        if (msg!=null && msg.getPerformative() == ACLMessage.REQUEST) {

            System.out.println(myAgent.getLocalName() + " recebeu mensagem: " + msg.getContent() + " vinda de: " + msg.getSender().getName());

            String produto_pedido = msg.getContent();
            ACLMessage reply = msg.createReply();

            /* Se o produto existir, assinalar a sua venda */
            if(produto_preco.containsKey(produto_pedido)){
                produto_vendas.put(produto_pedido, produto_vendas.get(produto_pedido) + 1);
                reply.setContent(produto_pedido);
                reply.setPerformative(ACLMessage.CONFIRM);
            }
            else{
                System.out.println("Produto não encontrado!");
                reply.setContent(produto_pedido);
                reply.setPerformative(ACLMessage.REFUSE);
            }

            myAgent.send(reply);
        }
        block();
    }
}
