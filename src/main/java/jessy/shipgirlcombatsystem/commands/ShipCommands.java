/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.commands;

import jessy.shipgirlcombatsystem.map.Direction;
import jessy.shipgirlcombatsystem.map.HexMap;
import jessy.shipgirlcombatsystem.map.Hex;
import jessy.shipgirlcombatsystem.ship.Ship;
import jessy.shipgirlcombatsystem.util.Phase;

/**
 *
 * @author dirk
 */
public class ShipCommands {
    public static Command moveForward(Ship s) {
        return new MoveForwardCommand(s);
    }
    
    public static Command turnLeft(Ship s) {
        return new TurnLeftCommand(s);
    }
    
    public static Command turnRight(Ship s) {
        return new TurnRightCommand(s);
    }
    
    public static Command drift(Ship s, Direction dir) {
        return new DriftCommand(s, dir);
    }

    private static class MoveForwardCommand implements Command {
        final String entityId;

        public MoveForwardCommand(Ship s) {
            entityId = s.getEntityId();
        }

        @Override
        public void applyCommand(HexMap board) {
            Ship ship = (Ship) board.getEntity(entityId);
            Hex h = ship.advance(1);
            board.move(entityId, board.getLocation(entityId).add(h));
        }

        @Override
        public void undoCommand(HexMap board) {
            Ship ship = (Ship) board.getEntity(entityId);
            Hex h = ship.advance(-1);
            board.move(entityId, board.getLocation(entityId).add(h));
        }

        @Override
        public boolean appliesToPhase(Phase p) {
            return p == Phase.MOVEMENT_PHASE;
        }
    }

    private static class TurnLeftCommand implements Command {
        final String entityId;

        public TurnLeftCommand(Ship s) {
            entityId = s.getEntityId();
        }

        @Override
        public void applyCommand(HexMap board) {
            Ship ship = (Ship) board.getEntity(entityId);
            ship.turnLeft();
        }

        @Override
        public void undoCommand(HexMap board) {
            Ship ship = (Ship) board.getEntity(entityId);
            ship.turnRight();
        }
        
        @Override
        public boolean appliesToPhase(Phase p) {
            return p == Phase.MOVEMENT_PHASE;
        }
    }
    
    private static class TurnRightCommand implements Command {
        final String entityId;

        public TurnRightCommand(Ship s) {
            entityId = s.getEntityId();
        }

        @Override
        public void applyCommand(HexMap board) {
            Ship ship = (Ship) board.getEntity(entityId);
            ship.turnRight();
        }

        @Override
        public void undoCommand(HexMap board) {
            Ship ship = (Ship) board.getEntity(entityId);
            ship.turnLeft();
        }
        
        @Override
        public boolean appliesToPhase(Phase p) {
            return p == Phase.MOVEMENT_PHASE;
        }
    }

    private static class DriftCommand implements Command {
        final String entityId;
        final Direction dir;

        public DriftCommand(Ship s, Direction dir) {
            this.dir = dir;
            entityId = s.getEntityId();
            
        }

        @Override
        public void applyCommand(HexMap board) {
            Ship ship = (Ship) board.getEntity(entityId);
            Hex h = ship.drift(dir, 1);
            board.move(entityId, board.getLocation(entityId).add(h));
        }

        @Override
        public void undoCommand(HexMap board) {
            Ship ship = (Ship) board.getEntity(entityId);
            Hex h = ship.drift(dir, -1);
            board.move(entityId, board.getLocation(entityId).add(h));
        }
        
        @Override
        public boolean appliesToPhase(Phase p) {
            return p == Phase.MOVEMENT_PHASE;
        }
    }
}
