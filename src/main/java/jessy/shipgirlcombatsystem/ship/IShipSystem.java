
package jessy.shipgirlcombatsystem.ship;

import javax.swing.JPanel;
import jessy.shipgirlcombatsystem.map.HexMap;
import jessy.shipgirlcombatsystem.thrift.ThriftEquipment;

/**
 *
 * @author dirk
 */
public interface IShipSystem {
    public String getName();
    public JPanel getSystemPanel();
    public boolean used();
    public boolean isDamaged();
    public void setDamaged();
    public void repair();
    
    //config events
    public void addToShip(Ship ship);
    public void removeFromShip(Ship ship);
    
    //server only events;
    public void startMovement(HexMap board); //do newtonian during this
    public void startAction(HexMap board); //starts the action phase.
    public void endTurn(HexMap board); //ends the action pahse

    public ThriftEquipment thrift();
}
