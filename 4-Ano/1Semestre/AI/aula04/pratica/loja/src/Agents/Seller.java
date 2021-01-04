package Agents;

import java.io.IOException;
import java.util.HashMap;

import Classes.Report;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class Seller extends Agent {

	private HashMap<String, Integer> products_sold = new HashMap<>();
	private HashMap<String, Integer> products_value = new HashMap<>();

	private int total_customers;

	protected void setup() {
		super.setup();

		// Register Agent
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(this.getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setName(this.getAID().getLocalName());
		// Register in DF with Agent Name: "Seller1", "Seller2" or "Seller3"
		// depending
		// on which container is located
		sd.setType(getLocalName());
		dfd.addServices(sd);

		try {
			DFService.register(this, dfd);
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}

		// Prepare Variables
		total_customers = 0;
		products_sold.put("A", 0);
		products_sold.put("B", 0);
		products_sold.put("C", 0);
		products_sold.put("D", 0);
		products_value.put("A", 5);
		products_value.put("B", 2);
		products_value.put("C", 7);
		products_value.put("D", 3);

		this.addBehaviour(new ReceberPedidosEProdutos());

	}

	private class ReceberPedidosEProdutos extends CyclicBehaviour {
		public void action() {
			ACLMessage msg = receive();
			if (msg != null && msg.getPerformative() == ACLMessage.REQUEST) {

				String clienteP = msg.getSender().getLocalName();
				ACLMessage resp = msg.createReply();
				String produtoPedido = msg.getContent();

				if (products_value.containsKey(produtoPedido)) {
					if (produtoPedido.equals("A")) {
						products_sold.put("A", products_sold.get("A") + 1);
						resp.setContent("A");
						resp.setPerformative(ACLMessage.CONFIRM);
					} else if (produtoPedido.equals("B")) {
						products_sold.put("B", products_sold.get("B") + 1);
						resp.setContent("B");
						resp.setPerformative(ACLMessage.CONFIRM);
					} else if (produtoPedido.equals("C")) {
						products_sold.put("C", products_sold.get("C") + 1);
						resp.setContent("C");
						resp.setPerformative(ACLMessage.CONFIRM);
					} else if (produtoPedido.equals("D")) {
						products_sold.put("D", products_sold.get("D") + 1);
						resp.setContent("D");
						resp.setPerformative(ACLMessage.CONFIRM);
					}
					
					total_customers++;
					
					// System.out.println("--------------------------------\n" +
					// myAgent.getLocalName() + ": Produto " + produtoPedido + "
					// requisitado por " + clienteP);
				
				} else {
					
					// System.out.println(myAgent.getLocalName() + ": Produto "
					// + produtoPedido + " pedido por " + clienteP + " nao
					// disponivel!");
					
					resp.setContent(produtoPedido);
					resp.setPerformative(ACLMessage.REFUSE);
				}
				myAgent.send(resp);

			} else if (msg != null && msg.getPerformative() == ACLMessage.REQUEST_WHENEVER) {

				try {

					float total = products_value.get("A") * products_sold.get("A")
							+ products_value.get("B") * products_sold.get("B")
							+ products_value.get("C") * products_sold.get("C")
							+ products_value.get("D") * products_sold.get("D");

					float total_avg = 0;
					if (total > 0 || total_customers > 0) {
						total_avg = total / total_customers;
					}

					// Find product most sold
					String product_most_sold = findMax(products_sold.get("A"), products_sold.get("B"),
							products_sold.get("C"), products_sold.get("D"));

					Report report_instance = new Report(total, total_avg, product_most_sold,
							products_sold.get(product_most_sold));

					// String clienteP = msg.getSender().getLocalName();
					ACLMessage resp = msg.createReply();

					resp.setContentObject(report_instance);

					resp.setPerformative(ACLMessage.CONFIRM);
					myAgent.send(resp);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// System.out.println(myAgent.getLocalName() + ": Message sent
				// to Analyst");
			}
			block();
		}
	}

	protected void takeDown() {
		super.takeDown();
		// De-register Agent from DF before killing it
		try {
			DFService.deregister(this);
		} catch (Exception e) {
		}
	}

	// Find the product most sold
	private String findMax(int... vals) {
		int max = Integer.MIN_VALUE;
		String res = new String();
		int pos = 1;

		for (int d : vals) {
			if (d > max) {
				max = d;
				// Switch position by Product String
				switch (pos) {
				case 1:
					res = "A";
					break;
				case 2:
					res = "B";
					break;
				case 3:
					res = "C";
					break;
				case 4:
					res = "D";
					break;
				}
			}
			pos++;
		}
		return res;
	}
}
