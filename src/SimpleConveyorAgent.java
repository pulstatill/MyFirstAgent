
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
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
public class SimpleConveyorAgent extends Agent
{

    private boolean inputSensor;
    private boolean outputSensor;
    private ConveyorInterface conveyorInterface;
    private boolean countingTime;
    private long timeTag, timeLimit;

    @Override
    protected void setup()
    {
        System.out.println("Hello World!");
        inputSensor = false;
        outputSensor = false;
        countingTime = false;

        timeTag = 0;
        timeLimit = 10000;

        conveyorInterface = new ConveyorHWStation2();

        addBehaviour(new CheckSensorsBehaviour(this, 20));
        addBehaviour(new ActuateBehaviour(this, 20));
        addBehaviour(new ReplyToMonitorRequests(this, MessageTemplate.MatchPerformative(ACLMessage.REQUEST)));
        
        RegisterInDF();
    }

    @Override
    protected void takeDown()
    {
        System.out.println("Good Bye!");
    }

    public void readSensor()
    {
        inputSensor = conveyorInterface.readInputSensor();
        outputSensor = conveyorInterface.readOutputSensor();
    }
    
    public boolean isInputSensor()
    {
        return inputSensor;
    }
    
    public boolean isOutputSensor()
    {
        return outputSensor;
    }

    public void operateConveyorBelt()
    {
        if (outputSensor)
        {
            conveyorInterface.stopConveyor();
            countingTime = false;
        } else if (!countingTime)
        {
            conveyorInterface.startConveyor();
            timeTag = System.currentTimeMillis();
            countingTime = true;
        } else if (countingTime && System.currentTimeMillis() - timeTag > timeLimit)
        {
            conveyorInterface.stopConveyor();
        }

        if (inputSensor)
        {
            conveyorInterface.startConveyor();
            timeTag = System.currentTimeMillis();
            countingTime = true;
        }
    }

    public void RegisterInDF()
    {
        try
        {
            DFAgentDescription dfd = new DFAgentDescription();
            dfd.setName(this.getAID());
            ServiceDescription sd = new ServiceDescription();
            sd.setName("ConveyorService");
            sd.setType("ConveyorServiceControl");
            dfd.addServices(sd);
            DFService.register(this, dfd);
        } catch (FIPAException ex)
        {
            Logger.getLogger(SimpleConveyorAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
