
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author philipp
 */
public class ConversationManager extends AchieveREInitiator
{
    
    public ConversationManager(Agent a, ACLMessage msg)
    {
        super(a, msg);
    }
    
    @Override
    protected void handleInform(ACLMessage inform)
    {
        ((MonitorAgent)myAgent).printMessageContent(inform.getContent());
    }
    
    @Override
    protected void handleRefuse(ACLMessage refuse)
    {
        ((MonitorAgent)myAgent).printMessageContent(refuse.getContent() + "refused");
    }
    
    @Override
    protected void handleAgree(ACLMessage agree)
    {
        ((MonitorAgent)myAgent).printMessageContent(agree.getContent() + " agree");
    }
    
    @Override
    protected void handleFailure(ACLMessage failure)
    {
        ((MonitorAgent)myAgent).printMessageContent(failure.getContent());
    }
}
