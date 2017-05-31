/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author philipp
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Runtime rt = Runtime.instance();
        //rt.setCloseVM(true);
        System.out.println("runtime created");

        Profile profile = new ProfileImpl(null, 1200, null);
        System.out.println("profile created");
        AgentContainer mainContainer = rt.createMainContainer(profile);
        ProfileImpl pContainer = new ProfileImpl(null, 1200, null);
        System.out.println("Launching the agent container ..."+pContainer);
        AgentContainer cont = rt.createAgentContainer(pContainer);
        System.out.println("Launching the agent container after ..." +pContainer);
        System.out.println("containers created");
        System.out.println("Launching the rma agent on the main container ...");
        try
        {
            AgentController rma = mainContainer.createNewAgent("rma", "jade.tools.rma.rma", new Object[0]);
            rma.start();
            AgentController createNewAgent = cont.createNewAgent("007", "FirstAgent", new Object[0]);
            createNewAgent.start();
            AgentController simp = cont.createNewAgent("SimpleConveyor", "SimpleConveyorAgent", new Object[0]);
            simp.start();
            AgentController mon = cont.createNewAgent("MonitorAgent", "MonitorAgent", new Object[0]);
            mon.start();
        } catch (StaleProxyException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
