
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author philipp
 */
public class ActuateBehaviour extends TickerBehaviour
{

    public ActuateBehaviour(Agent a, long period)
    {
        super(a, period);
    }

    @Override
    protected void onTick()
    {
        ((SimpleConveyorAgent)myAgent).operateConveyorBelt();
    }
    
}
