/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.map;

import java.awt.Color;
import java.awt.Graphics2D;
import jessy.shipgirlcombatsystem.thrift.ThriftShip;

/**
 *
 * @author dirk
 */
public interface BoardItem extends Cloneable {

    public String getEntityId();    
    public boolean selectable();
    public boolean drawable();
    
    /**
     * 
     * @param g2
     * @param size half the distance from the ceneter to the side walls. Also half an edge in length.
     * @param height the height from the center to the bottom/top.
     */
    public void drawItem(Graphics2D g2, Hex position, int size, int height);
 
    //events
    public void notifySelect(HexMap board);
    //server only events;
    public void startMovement(HexMap board); //do newtonian during this
    public void startAction(HexMap board); //starts the action phase.
    public void endTurn(HexMap board); //ends the action pahse

    
    public BoardItem clone();

    public ThriftShip thrift();

    public Player getOwner();

}
