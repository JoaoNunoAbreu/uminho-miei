package Behaviours;

import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

public class Sender_Behaviour extends SimpleBehaviour {

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if(msg != null){
            System.out.println(" == Answer" + " <- " + msg.getContent()
            + " == from " + msg.getSender().getName());
            myAgent.doDelete();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
