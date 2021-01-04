package Agents;

import Classes.MakeRequest;
import Classes.Position;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.Random;

public class Client extends Agent {

    @Override
    protected void setup() {
        super.setup();
        this.addBehaviour(new Request());
        this.addBehaviour(new Reply());
    }

    @Override
    protected void takeDown() {
        super.takeDown();
        System.out.println(this.getLocalName() + " a morrer...");
    }

    private static class Request extends OneShotBehaviour {

        public void action() {

            DFAgentDescription template = new DFAgentDescription();
            ServiceDescription sd = new ServiceDescription();
            sd.setType("central");
            template.addServices(sd);

            try {
                DFAgentDescription[] result = DFService.search(myAgent, template);

                if (result.length > 0) {
                    Random rand = new Random();
                    Position init = new Position(rand.nextInt(100), rand.nextInt(100));
                    Position dest = new Position(rand.nextInt(100), rand.nextInt(100));

                    MakeRequest mr = new MakeRequest(myAgent.getAID(), init, dest);
                    ACLMessage mensagem = new ACLMessage(ACLMessage.REQUEST);

                    for (DFAgentDescription df : result) {
                        mensagem.addReceiver(df.getName());
                    }

                    mensagem.setContentObject(mr);
                    myAgent.send(mensagem);
                }

            } catch (FIPAException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class Reply extends CyclicBehaviour {

        public void action() {
            ACLMessage msg = receive();
            if (msg != null) {
                myAgent.doDelete();
            }
            else{
                block();
            }

        }
    }
}
