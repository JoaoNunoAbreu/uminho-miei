package Agents;

import Behaviours.EsperaConfirmacaoBehaviour;
import Behaviours.InteresseComprarBehaviour;
import jade.core.Agent;

public class Buyer extends Agent {
    protected void setup(){
        super.setup();
        addBehaviour(new InteresseComprarBehaviour(this,1000));
        addBehaviour(new EsperaConfirmacaoBehaviour());

    }

    protected void takeDown(){
        super.takeDown();
        System.out.println(this.getLocalName() + " a morrer...");
    }
}
