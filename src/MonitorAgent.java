
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author philipp
 */
public class MonitorAgent extends Agent
{
    @Override
    protected void setup()
    {
        DFAgentDescription[] conveyorAgents = FindAgents("ConveyorService", "ConveyorServiceControl");
        addBehaviour(new RequestData(this, 1000, conveyorAgents));
    }
    
    @Override
    protected void takeDown()
    {
        
    }
    
    public DFAgentDescription[] FindAgents(String serviceName, String serviceType)
    {
        try
        {
            DFAgentDescription[] agents = null;
            DFAgentDescription ad = new DFAgentDescription();
            ServiceDescription sd = new ServiceDescription();
            sd.setType(serviceType);
            sd.setName(serviceName);
            ad.addServices(sd);
            agents = DFService.search(this, ad);
            return agents;
            
        } catch (FIPAException ex)
        {
            Logger.getLogger(MonitorAgent.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void printMessageContent (String messageContent)
    {
        System.out.println(messageContent);
    }
}
