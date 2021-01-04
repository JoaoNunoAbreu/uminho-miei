import jade.core.Agent;

public class HelloAgent extends Agent{
    protected void setup(){
        super.setup();
        System.out.println(this.getLocalName() + " is saying Hello World!");
    }

    protected void takeDown(){
        super.takeDown();

        System.out.println(this.getLocalName() + " is saying Bye World!");
    }
}