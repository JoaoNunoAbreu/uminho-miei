package Behaviours;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class Receiver_Behaviour extends CyclicBehaviour {
    @Override
    public void action() {

        ACLMessage msg = myAgent.receive();
        if (msg!=null) {
            System.out.println(" == Answer <- "
                    +  msg.getContent() + " == from "
                    +  msg.getSender().getName() );

            ACLMessage reply = msg.createReply();
            reply.setPerformative( ACLMessage.INFORM );
            reply.setContent("Welcome " + msg.getSender().getName());
            myAgent.send(reply);
        }
        block();
    }
}
