package Agents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Classes.Report;
import jade.core.AID;
import jade.core.Agent;
import jade.core.ContainerID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

public class Analyst extends Agent {

	private HashMap<String, Report> seller_reports = new HashMap<>();
	private List<String> available_containers = new ArrayList<>();

	private int pos_location = -1;
	// private Location nextSite;

	protected void setup() {
		super.setup();

		Object[] args = getArguments();
		for (Object a : args) {
			available_containers.add(a.toString());
		}

		this.addBehaviour(new PedirLucro(this, 10000));
		this.addBehaviour(new ReceberQuery());

	}

	// Trigger to start Query: doMove(Container1) and query selling Report
	private class PedirLucro extends TickerBehaviour {

		public PedirLucro(Agent a, long period) {
			super(a, period);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void onTick() {
			// TODO Auto-generated method stub

			pos_location = 0;

			ContainerID location = new ContainerID();
			location.setName(available_containers.get(pos_location));
			myAgent.doMove(location);

			// Update position based on args list

			String ContainerID = available_containers.get(pos_location).substring("Container".length());

			AID provider = new AID("Seller" + ContainerID, AID.ISLOCALNAME);
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST_WHENEVER);
			msg.addReceiver(provider);
			myAgent.send(msg);

		}

	}

	private class ReceberQuery extends CyclicBehaviour {

		public void action() {
			ACLMessage msg = receive();
			if (msg != null && msg.getPerformative() == ACLMessage.CONFIRM) {

				// System.out.println(myAgent.getLocalName() + ": Report received from " + msg.getSender());

				try {
					// Add report to the list
					Report received_report = (Report) msg.getContentObject();
					seller_reports.put(msg.getSender().getLocalName(), received_report);
					pos_location++;

					// Reached the end of the line? Report Results
					if (pos_location >= available_containers.size()) {

						ContainerID location = new ContainerID();
						location.setName("Container0");
						myAgent.doMove(location);

						// Report conclusions and go back to Container1
						System.out.println("--------------------------");
						System.out.println("Containers Report:");
						System.out.println("--------------------------");

						int glob_profit = 0;
						int glob_avg_profit = 0;
						String glob_product = new String();
						int glob_quantity = 0;
						int i = 1;

						for (Map.Entry<String, Report> entry : seller_reports.entrySet()) {
							String seller = entry.getKey();
							Report report = entry.getValue();

							glob_profit += report.getProfit();
							glob_avg_profit += report.getAvg_profit();

							if (glob_quantity < report.getQuantity_most_sold()) {
								glob_quantity = report.getQuantity_most_sold();
								glob_product = report.getProduct_most_sold();
							}

							System.out.println("\tSeller" + (i));
							System.out.println("\t\tProfit: " + report.getProfit());
							System.out.println("\t\tAvg_Profit: " + report.getAvg_profit());
							System.out.println("\t\tProduct Most sold: " + report.getProduct_most_sold());
							System.out.println("\t\tQuantity sold: " + report.getQuantity_most_sold());
							System.out.println("--------------------------");

							i++;

						}

						System.out.println("Global Report:");
						System.out.println("--------------------------");
						System.out.println("\t\tProfit: " + glob_profit);
						System.out.println("\t\tAvg_Profit: " + glob_avg_profit);
						System.out.println("\t\tProduct Most sold: " + glob_product);
						System.out.println("\t\tQuantity sold: " + glob_quantity);
						System.out.println("--------------------------");

					} // If not, continue to next container!
					else {

						ContainerID location = new ContainerID();
						location.setName(available_containers.get(pos_location));
						myAgent.doMove(location);

						// Get ContainerID
						String ContainerID = available_containers.get(pos_location).substring("Container".length());

						ACLMessage msg1 = new ACLMessage(ACLMessage.REQUEST_WHENEVER);
						AID provider = new AID("Seller" + ContainerID, AID.ISLOCALNAME);
						msg1.addReceiver(provider);
						myAgent.send(msg1);

					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			block();

		}
	}

	protected void takeDown() {
		super.takeDown();
	}

}