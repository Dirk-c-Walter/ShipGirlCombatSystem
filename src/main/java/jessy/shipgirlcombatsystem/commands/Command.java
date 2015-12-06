/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.commands;

import jessy.shipgirlcombatsystem.map.HexMap;
import jessy.shipgirlcombatsystem.thrift.ThriftCommand;
import jessy.shipgirlcombatsystem.util.Phase;

/**
 *
 * @author dirk
 */
public interface Command {
    public boolean appliesToPhase(Phase p);
    public void applyCommand(HexMap board);
    public void undoCommand(HexMap board);

    public ThriftCommand thrift();
}
