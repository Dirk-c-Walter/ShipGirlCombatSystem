
package jessy.shipgirlcombatsystem.ship.systems;

import javax.swing.JPanel;
import jessy.shipgirlcombatsystem.ship.FireingArcs;
import jessy.shipgirlcombatsystem.ship.IShipSystem;
import jessy.shipgirlcombatsystem.ship.RangeFactor;

/**
 *
 * @author dirk
 */
public class ShipWeaponSystem implements IShipSystem {
    public RangeFactor range = RangeFactor.MELEE;
    public FireingArcs arc = FireingArcs.NONE;
    

    public ShipWeaponSystem() {
        
    }
    
    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JPanel getSystemPanel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean used() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDamaged() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDamaged() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void repair() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
