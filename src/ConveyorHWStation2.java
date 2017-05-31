/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author philipp
 */
public class ConveyorHWStation2 implements ConveyorInterface
{

    @Override
    public boolean readInputSensor()
    {
        int swtch = ((int)(Math.random()*2));
        switch(swtch)
        {
            case 0: return false;
            case 1: return true;
        }
        return false;
    }

    @Override
    public boolean readOutputSensor()
    {
        int swtch = ((int)(Math.random()*2));
        switch(swtch)
        {
            case 0: return false;
            case 1: return true;
        }
        return false;
    }

    @Override
    public void startConveyor()
    {
        //System.out.println("Start Conveyor");
    }

    @Override
    public void stopConveyor()
    {
        //System.out.println("Stop Conveyor");
    }
    
}
