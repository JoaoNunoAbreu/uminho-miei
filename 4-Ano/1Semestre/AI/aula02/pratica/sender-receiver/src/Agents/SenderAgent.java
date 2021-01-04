package Agents;

import Behaviours.Sender_Behaviour;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class SenderAgent extends Agent{

    @Override
    protected void setup(){

        // Prepare message
        AID receiver = new AID("Receiver",AID.ISLOCALNAME);
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent("Hello World!");
        msg.setConversationId(""+System.currentTimeMillis());
        msg.addReceiver(receiver);

        send(msg);

        addBehaviour(new Sender_Behaviour());
    }

    @Override
    protected void takeDown() {
        super.takeDown();
        System.out.println(this.getLocalName() + " a morrer...");
    }
}