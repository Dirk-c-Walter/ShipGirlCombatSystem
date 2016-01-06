/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.net;

import java.util.Map;
import jessy.shipgirlcombatsystem.map.HexMap;

/**
 *
 * @author dirk
 */
public abstract class DamageSystem  {
    public abstract String applyHit(HexMap board, String sourceEntityId, String targetEntityId, Map<String, String> weaponStats);
}
