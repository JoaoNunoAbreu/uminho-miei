package Behaviours;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class EsperaConfirmacaoBehaviour extends CyclicBehaviour {
    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            if (msg.getPerformative() == ACLMessage.CONFIRM) {
                System.out.println(myAgent.getLocalName() + ": Reply CONFIRM = Product " + msg.getContent());
            }
            else if (msg.getPerformative() == ACLMessage.REFUSE) {
                System.out.println(myAgent.getLocalName() + ": Reply REFUSE = Product " + msg.getContent());
            }
        }
    }
}
