
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author philipp
 */
public class RequestData extends TickerBehaviour
{
    private DFAgentDescription[] conveyorAgents;

    public RequestData(Agent a, long period, DFAgentDescription[] conveyorAgents)
    {
        super(a, period);
        this.conveyorAgents = conveyorAgents;
    }

    @Override
    protected void onTick()
    {
        for (int i = 0; i < conveyorAgents.length; i++)
        {
            ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
            msg.addReceiver(conveyorAgents[i].getName());
            msg.setContent("Hello Conveyor");
            myAgent.addBehaviour(new ConversationManager(myAgent, msg));
            
        }
    }
    
}
