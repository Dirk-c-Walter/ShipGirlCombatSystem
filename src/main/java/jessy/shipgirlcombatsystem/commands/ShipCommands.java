/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.commands;

import java.util.HashMap;
import java.util.Map;
import jessy.shipgirlcombatsystem.map.Direction;
import jessy.shipgirlcombatsystem.map.HexMap;
import jessy.shipgirlcombatsystem.map.Hex;
import jessy.shipgirlcombatsystem.screens.MapPanel;
import jessy.shipgirlcombatsystem.ship.Ship;
import jessy.shipgirlcombatsystem.ship.systems.ShipWeaponSystem;
import jessy.shipgirlcombatsystem.thrift.ThriftCommand;
import jessy.shipgirlcombatsystem.thrift.ThriftCommandEnum;
import jessy.shipgirlcombatsystem.util.Phase;

/**
 *
 * @author dirk
 */
public class ShipCommands {
    public static Command fromEnum(ThriftCommandEnum cmd, ThriftCommand item){
        switch(cmd) {
            case Advance: return new MoveForwardCommand(item.sourceId);
            case TurnLeft: return new TurnLeftCommand(item.sourceId);
            case TurnRight: return new TurnRightCommand(item.sourceId);
            case DriftN: return new DriftCommand(item.sourceId, Direction.NORTH);
            case DriftNE: return new DriftCommand(item.sourceId, Direction.NORTHEAST);
            case DriftSE: return new DriftCommand(item.sourceId, Direction.SOUTHEAST);
            case DriftS: return new DriftCommand(item.sourceId, Direction.SOUTH);
            case DriftSW: return new DriftCommand(item.sourceId, Direction.SOUTHWEST);
            case DriftNW: return new DriftCommand(item.sourceId, Direction.NORTHWEST);
        }
        
        return null;
    }
    
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

    public static Command fireWeapon(ShipWeaponSystem system, Hex startingHex, Ship target, Hex targetHex) {
        return new FireWeaponCommand(system, startingHex, targetHex, target);
    }

    private static class MoveForwardCommand implements Command {
        final String entityId;

        public MoveForwardCommand(Ship s) {
            entityId = s.getEntityId();
        }
        
        public MoveForwardCommand(String s) {
            entityId = s;
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

        @Override
        public ThriftCommand thrift() {
            ThriftCommand cmd = new ThriftCommand();
            cmd.setSourceId(entityId);
            cmd.setType("Enum");
            cmd.setCommandCode(ThriftCommandEnum.Advance);
            return cmd;
        }
    }

    private static class TurnLeftCommand implements Command {
        final String entityId;

        public TurnLeftCommand(Ship s) {
            entityId = s.getEntityId();
        }
        
        public TurnLeftCommand(String s) {
            entityId = s;
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
        
        @Override
        public ThriftCommand thrift() {
            ThriftCommand cmd = new ThriftCommand();
            cmd.setSourceId(entityId);
            cmd.setType("Enum");
            cmd.setCommandCode(ThriftCommandEnum.TurnLeft);
            return cmd;
        }
    }
    
    private static class TurnRightCommand implements Command {
        final String entityId;

        public TurnRightCommand(Ship s) {
            entityId = s.getEntityId();
        }
        
        public TurnRightCommand(String s) {
            entityId = s;
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
        
        @Override
        public ThriftCommand thrift() {
            ThriftCommand cmd = new ThriftCommand();
            cmd.setSourceId(entityId);
            cmd.setType("Enum");
            cmd.setCommandCode(ThriftCommandEnum.TurnRight);
            return cmd;
        }
    }

    private static class DriftCommand implements Command {
        final String entityId;
        final Direction dir;

        public DriftCommand(Ship s, Direction dir) {
            this.dir = dir;
            entityId = s.getEntityId();
        }
        
        public DriftCommand(String s, Direction dir) {
            this.dir = dir;
            entityId = s;
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
        
        @Override
        public ThriftCommand thrift() {
            ThriftCommand cmd = new ThriftCommand();
            cmd.setSourceId(entityId);
            cmd.setType("Enum");
            switch(dir) {
                case NORTH: cmd.setCommandCode(ThriftCommandEnum.DriftN); break;
                case NORTHEAST: cmd.setCommandCode(ThriftCommandEnum.DriftNE); break;
                case SOUTHEAST: cmd.setCommandCode(ThriftCommandEnum.DriftSE); break;
                case SOUTH: cmd.setCommandCode(ThriftCommandEnum.DriftS); break;
                case SOUTHWEST: cmd.setCommandCode(ThriftCommandEnum.DriftSW); break;
                case NORTHWEST: cmd.setCommandCode(ThriftCommandEnum.DriftNW); break;
            }
            return cmd;
        }
    }

    private static class FireWeaponCommand implements Command {
        final ShipWeaponSystem system; //will be null on server side.
        final Map<String, String> weaponStats;
        

        public FireWeaponCommand(ShipWeaponSystem system, Hex startingHex, Hex targetHex, Ship target) {
            final String sourceEntityId = system.getShip().getEntityId();
            final String targetEntityId = target.getEntityId();
            this.system = system;
            weaponStats = new HashMap<String, String>();
            weaponStats.put("targetEntityId", targetEntityId);
            weaponStats.put("sourceEntityId", sourceEntityId);
            weaponStats.put("Heat", "" + system.getHeat());
            weaponStats.put("Shield Damage", "" + system.getShieldDmg());
            weaponStats.put("Shield Penetration", "" + system.getShieldPen());
            weaponStats.put("WeaponPower", "" + system.getWeaponPower(startingHex, targetHex));
            weaponStats.put("Hull Damage", "" + system.getHullDmg());
            weaponStats.put("WeaponName", system.getName());
        }

        @Override
        public boolean appliesToPhase(Phase p) {
            return p == Phase.ACTION_PHASE;
        }

        @Override
        public void applyCommand(HexMap board) {
            if(system != null) {
                system.setUsed(true);
            } else {
                board.addServerCommand(new ServerCommand() {
                    @Override
                    public void endActionPhase(HexMap board) {
                        final Ship source = (Ship) board.getEntity(weaponStats.get("sourceEntityId"));
                        final Ship target = (Ship) board.getEntity(weaponStats.get("targetEntityId"));
                        
                        int modPower = Integer.parseInt(weaponStats.get("WeaponPower")); //TODO: mod with random and sensor power in future
                        target.applyHit(modPower, Integer.parseInt(weaponStats.get("Shield Damage")),
                                Integer.parseInt(weaponStats.get("Shield Penetration")),
                                Integer.parseInt(weaponStats.get("Hull Damage")));
                    }
                    
                });
            }
            
        }

        @Override
        public void undoCommand(HexMap board) {
            if(system != null) {
                system.setUsed(false);
            }
        }
        
        public FireWeaponCommand(ThriftCommand cmd) {
            assert(cmd.type.equals("FireShipWeapon"));
            weaponStats = cmd.properties;
            system = null;
            
        }

        @Override
        public ThriftCommand thrift() {
            ThriftCommand thrift = new ThriftCommand();
            thrift.setType("FireShipWeapon");
            thrift.properties = this.weaponStats;
            thrift.sourceId = weaponStats.get("sourceEntityId");
            return thrift;
        }
    }
}
