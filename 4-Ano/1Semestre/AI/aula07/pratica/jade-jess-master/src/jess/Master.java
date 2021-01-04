package jess;

import jade.core.behaviours.TickerBehaviour;
import temperature.QuerySensorTemperatureBehaviour;

public class Master extends JessAgentBase {
    @Override
    protected void setup() {

        // query all sensors
        addBehaviour(new TickerBehaviour(this, 9000) {
            @Override
            protected void onTick() {
                addBehaviour(new QuerySensorTemperatureBehaviour(myAgent));
            }
        });

    }
}
