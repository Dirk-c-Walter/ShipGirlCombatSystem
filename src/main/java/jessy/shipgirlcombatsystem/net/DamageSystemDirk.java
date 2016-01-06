/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.net;

import java.util.Map;
import jessy.shipgirlcombatsystem.commands.ShipCommands.FireWeaponCommand;
import jessy.shipgirlcombatsystem.map.HexMap;
import jessy.shipgirlcombatsystem.ship.Ship;

/**
 *
 * @author dirk
 */
public class DamageSystemDirk extends DamageSystem {
    
    public String applyHit(HexMap board, String sourceEntityId, String targetEntityId, Map<String, String> weaponStats) {
        final Ship source = (Ship) board.getEntity(sourceEntityId);
        final Ship target = (Ship) board.getEntity(targetEntityId);

        int modPower = Integer.parseInt(weaponStats.get("WeaponPower")) + Server.getRandomMod(); //TODO: mod with sensor power in future
        int modSensor = target.getSensorResults(source.getOwner()) + Server.getRandomMod();
        return applyHit(target, modPower, modSensor, Integer.parseInt(weaponStats.get("Shield Damage")),
                Integer.parseInt(weaponStats.get("Shield Penetration")),
                Integer.parseInt(weaponStats.get("Hull Damage")));
    }
    
    public String applyHit(Ship target, int modPower, int modSensor, int shieldDmg, int shieldPen, int hullDmg) {
        int mod = Math.min(modPower, modSensor);
        if(mod <= 0) {
            return "The shot missed with power " + mod + " (" + modPower + ", " + modSensor + ").";
        }
        
        final int postShield = target.shield.applyHit(mod, shieldDmg, shieldPen);
        
        String str = "The shot hit, with power " + mod + " (" + modPower + ", " + modSensor + "). ";
        if(postShield > 0) {
            str += "Punching through the shields (" + target.shield.currentShield + "/" + target.shield.maxShield + ") with remaining power " + postShield + " ";
            str += target.hull.applyHit(postShield, hullDmg);
        } else {
            str += "However it failed to penetrate the shields (" + target.shield.currentShield + "/" + target.shield.maxShield + ") of " + target.id + ".";
        }
        
        return str;
    }
    
    private int applyShieldHit(int power, int shieldDmg, int shieldPen) {
        this.currentShield = Math.max(this.currentShield - shieldDmg, 0);
        return power - Math.max(currentShield - shieldPen, 0);
    }
    
}
