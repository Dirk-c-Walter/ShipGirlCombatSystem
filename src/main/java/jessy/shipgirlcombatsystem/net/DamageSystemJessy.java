/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.net;

import java.util.Map;
import jessy.shipgirlcombatsystem.map.HexMap;
import jessy.shipgirlcombatsystem.ship.Ship;

/**
 *
 * @author dirk
 */
public class DamageSystemJessy extends DamageSystem {

    @Override
    public String applyHit(HexMap board, String sourceEntityId, String targetEntityId, Map<String, String> weaponStats) {
        final Ship source = (Ship) board.getEntity(sourceEntityId);
        final Ship target = (Ship) board.getEntity(targetEntityId);

        int modPower = Integer.parseInt(weaponStats.get("WeaponPower")) + Server.getRandomMod(); //TODO: mod with sensor power in future
        int modSensor = target.getSensorResults(source.getOwner()) + Server.getRandomMod();
    }
    
}
