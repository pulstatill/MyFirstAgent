
import jade.core.Agent;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author philipp
 */
public class ReplyToMonitorRequests extends AchieveREResponder
{
    public ReplyToMonitorRequests (Agent agent, MessageTemplate template)
    {
        super(agent, template); 
    }
    
    @Override
    protected ACLMessage handleRequest(ACLMessage request) throws NotUnderstoodException, RefuseException
    {
        String messageContent = request.getContent();
        
        System.out.println("Got the message "+ messageContent + " from agent "+ request.getSender().getLocalName());
        
        ACLMessage reply;
        
        reply = request.createReply();
        
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(2);
        if(randomInt == 0)
        {
            reply.setPerformative(ACLMessage.REFUSE);
        } else
        {
            reply.setPerformative(ACLMessage.AGREE);
        }
        return reply;
    }
    
    @Override
    protected ACLMessage prepareResultNotification (ACLMessage request, ACLMessage response) throws FailureException
    {
        ACLMessage reply;
        reply = request.createReply();
        reply.setContent("My Sensor have the value: InputSensor -> " + ((SimpleConveyorAgent)myAgent).isInputSensor() + " OutpuntSensor -> "+ ((SimpleConveyorAgent)myAgent).isOutputSensor());
        reply.setPerformative(ACLMessage.INFORM);
        return reply;
    }
}

