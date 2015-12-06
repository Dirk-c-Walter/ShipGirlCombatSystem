/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.map;

import java.awt.Graphics2D;

/**
 *
 * @author dirk
 */
public interface BoardItem extends Cloneable {

    public String getEntityId();
    
    public void notifyRemoved(Hex a);

    public void notifyAdded(Hex b);
    
    public boolean selectable();
    public boolean drawable();
    
    /**
     * 
     * @param g2
     * @param size half the distance from the ceneter to the side walls. Also half an edge in length.
     * @param height the height from the center to the bottom/top.
     */
    public void drawItem(Graphics2D g2, Hex position, int size, int height);
    
    public void notifySelect();

    public void doNewtonian();
    
    public BoardItem clone();
}
