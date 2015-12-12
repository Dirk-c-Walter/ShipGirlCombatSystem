
package jessy.shipgirlcombatsystem.ship;

import javax.swing.JPanel;

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
}
