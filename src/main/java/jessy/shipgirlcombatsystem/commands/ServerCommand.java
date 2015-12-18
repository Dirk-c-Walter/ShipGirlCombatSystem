/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.commands;

import jessy.shipgirlcombatsystem.map.HexMap;

/**
 *
 * @author dirk
 */
public interface ServerCommand {
    public void endActionPhase(HexMap board);
}
