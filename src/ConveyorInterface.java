/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author philipp
 */
public interface ConveyorInterface
{
    public boolean readInputSensor();
    public boolean readOutputSensor();
    public void startConveyor();
    public void stopConveyor();
}
