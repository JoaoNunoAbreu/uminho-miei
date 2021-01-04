package Agents;

import Behaviours.Comprarprodutos;
import Behaviours.ReceberConfirmacao;
import jade.core.Agent;

public class Customer extends Agent {

	protected void setup() {
		super.setup();
		this.addBehaviour(new Comprarprodutos(this, 1000));
		this.addBehaviour(new ReceberConfirmacao(this));
	}

	protected void takeDown() {
		super.takeDown();
	}

}