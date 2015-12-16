
package jessy.shipgirlcombatsystem.ship.systems;

import java.util.List;
import javax.swing.JPanel;
import jessy.shipgirlcombatsystem.map.Hex;
import jessy.shipgirlcombatsystem.map.HexMap;
import jessy.shipgirlcombatsystem.ship.FireingArcs;
import jessy.shipgirlcombatsystem.ship.IShipSystem;
import jessy.shipgirlcombatsystem.ship.RangeFactor;
import jessy.shipgirlcombatsystem.ship.Ship;
import jessy.shipgirlcombatsystem.thrift.ThriftEquipment;

/**
 *
 * @author dirk
 */
public class ShipWeaponSystem implements IShipSystem {    
    private enum State {
        READY,
        DAMAGED,
        USED,
        REPAIRED;
    }
    
    private Ship ship;
    protected State state = State.READY;
    public RangeFactor range = RangeFactor.DISTANCE;
    public FireingArcs arc = FireingArcs.FULL;
    private int heat = 2;
    private int shieldDmg = 4;
    private int shieldPen = 5;
    private int power = 12;
    private int hullDmg = 1;
    String name;
    
    private final WeaponPanel panel;
    

    public ShipWeaponSystem(String name, Ship ship) {
        this.name = name;
        this.ship =ship;
        panel = new WeaponPanel(this);
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public WeaponPanel getSystemPanel() {
        return panel;
    }

    @Override
    public boolean used() {
        return state == State.USED;
    }
    
    public void startAction() {
        if(state != State.DAMAGED) {
            state = State.READY;
            panel.doUpdate();
        }
    }
    
    public void endAction() {
    }
    
    /**
     * This function should only be callsed by the ship class. and internal func
     * @param ship 
     */
    @Override
    public void addToShip(Ship ship) {
        this.ship = ship;
        //do stat mods if any.
    }
    
    /**
     * This function should only be callsed by the ship class. and internal func
     * @param ship 
     */
    @Override
    public void removeFromShip(Ship ship) {
        //remove stat mods if any.
    }

    @Override
    public boolean isDamaged() {
        return state == State.DAMAGED;
    }

    @Override
    public void setDamaged() {
        state = State.DAMAGED;
        removeFromShip(ship);
        panel.doUpdate();
    }

    @Override
    public void repair() {
        state = state.REPAIRED;
        addToShip(ship);
        panel.doUpdate();
    }

    public int getHeat() {
        return heat;
    }
    
    public void setHeat(int heat) {
        this.heat = heat;
        panel.doUpdate();
    }

    public RangeFactor getRange() {
        return range;
    }

    public void setRange(RangeFactor range) {
        this.range = range;
        panel.doUpdate();
    }

    public FireingArcs getArc() {
        return arc;
    }

    public void setArc(FireingArcs arc) {
        this.arc = arc;
        panel.doUpdate();
    }

    public int getShieldDmg() {
        return shieldDmg;
    }

    public void setShieldDmg(int shieldDmg) {
        this.shieldDmg = shieldDmg;
        panel.doUpdate();
    }

    public int getShieldPen() {
        return shieldPen;
    }

    public void setShieldPen(int shieldPen) {
        this.shieldPen = shieldPen;
        panel.doUpdate();
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
        panel.doUpdate();
    }

    public int getHullDmg() {
        return hullDmg;
    }

    public void setHullDmg(int hullDmg) {
        this.hullDmg = hullDmg;
        panel.doUpdate();
    }
    
     public Ship getShip() {
        return ship;
    }

    public boolean isValidTarget(List<Hex> line, Ship target) {
        final int distance = line.size();
        if(state != State.READY) {
            return false;
        }
        
        if(distance < 2) {
            return range == RangeFactor.MELEE || arc.canHitSameHex();
        } else {
            return arc.allowableFirstHex(ship.getFacing(), line.get(0)).contains(line.get(1));
        }
    }
    
    public ShipWeaponSystem(ThriftEquipment thrift, Ship ship) {
        assert(thrift.type.equals("ShipWeaponSystem"));
        assert(ship.getEntityId().equals(thrift.properties.get("shipId")));
        
        this.name = thrift.properties.get("Name");
        this.ship = ship;
        state = State.valueOf(thrift.properties.get("State"));
        range = RangeFactor.valueOf(thrift.properties.get("Range"));
        arc = FireingArcs.valueOf(thrift.properties.get("FiringArc"));
        heat = Integer.parseInt(thrift.properties.get("Heat"));
        shieldDmg = Integer.parseInt(thrift.properties.get("Shield Damage"));
        shieldPen = Integer.parseInt(thrift.properties.get("Shield Penetration"));
        power = Integer.parseInt(thrift.properties.get("Power"));
        hullDmg = Integer.parseInt(thrift.properties.get("Hull Damage"));
        
        panel = new WeaponPanel(this);
    }

    @Override
    public ThriftEquipment thrift() {
        ThriftEquipment thrift = new ThriftEquipment();
        thrift.type = "ShipWeaponSystem";
        thrift.putToProperties("shipId", ship.getEntityId());
        thrift.putToProperties("State", state.name());
        thrift.putToProperties("Range", range.name());
        thrift.putToProperties("FiringArc", arc.name());
        thrift.putToProperties("Heat", "" + heat);
        thrift.putToProperties("Shield Damage", "" + shieldDmg);
        thrift.putToProperties("Shield Penetration", "" + shieldPen);
        thrift.putToProperties("Power", "" + power);
        thrift.putToProperties("Hull Damage", "" + hullDmg);
        thrift.putToProperties("Name", name);

        return thrift;
    }
    
    //server only events;
    public void startMovement(HexMap board) {
    }
    
    public void startAction(HexMap board) {
        if(state != State.DAMAGED) {
            state = State.READY;
            panel.doUpdate();
        }
    }
    
    public void endTurn(HexMap board) {
        
    }
}
