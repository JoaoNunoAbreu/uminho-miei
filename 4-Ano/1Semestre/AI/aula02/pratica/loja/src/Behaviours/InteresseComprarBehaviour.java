package Behaviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InteresseComprarBehaviour extends TickerBehaviour {

    private final List<String> produtos;

    public InteresseComprarBehaviour(Agent a, long period) {
        super(a,period);
        produtos = new ArrayList<>();
        produtos.add("A");
        produtos.add("B");
        produtos.add("C");
        produtos.add("D");
        produtos.add("E");
        produtos.add("F");
        System.out.println("Lista de produtos criados!");
    }

    @Override
    public void onTick() {

        /* Escolhe um produto random */
        Random randomizer = new Random();
        String random_product = produtos.get(randomizer.nextInt(produtos.size()));

        AID receiver = new AID("Seller",AID.ISLOCALNAME);
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.addReceiver(receiver);
        msg.setContent(random_product);
        myAgent.send(msg);
    }

}
