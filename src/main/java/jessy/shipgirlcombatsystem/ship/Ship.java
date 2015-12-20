/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.ship;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.ImageIcon;
import jessy.shipgirlcombatsystem.map.BoardItem;
import jessy.shipgirlcombatsystem.map.Direction;
import jessy.shipgirlcombatsystem.map.Hex;
import jessy.shipgirlcombatsystem.map.HexMap;
import jessy.shipgirlcombatsystem.map.Player;
import jessy.shipgirlcombatsystem.net.Server;
import jessy.shipgirlcombatsystem.screens.MapPanel;
import jessy.shipgirlcombatsystem.ship.systems.ShipWeaponSystem;
import jessy.shipgirlcombatsystem.thrift.ThriftEquipment;
import jessy.shipgirlcombatsystem.thrift.ThriftShip;

/**
 *
 * @author dirk
 */
public class Ship implements BoardItem {
    private static final int overflow = 4;
    private final String id;
    private final Player owner;
    private String name;
    private int speedQ =0;
    private int speedR =0;
    private Direction facing = Direction.NORTH;
    private ImageIcon image = new javax.swing.ImageIcon(getClass().getResource("/jessy/shipgirlcombatsystem/images/BaseShip.png"));
    private Shield shield = new Shield();
    private Hull hull = new Hull();
    private int currentHeat =0;
    private int cooling = 5;
    private int ecm =0;
    private int sensorPower = 6;
    private boolean destroyed = false;
    private RangeFactor sensorRange = RangeFactor.DISTANCE;
    private List<IShipSystem> equipment = new ArrayList<>();
    private String imgName = "BaseShip";

    public Ship(String id, Player owner) {
        this.id = id;
        name= "Nautilus " + id;
        this.owner = owner;
    }
    
    public Ship(Ship other) {
        this(other, other.id);
    }
    
    public Ship(Ship other, String newId) {
        this.id = newId;
        name = other.name;
        this.owner = other.owner;
        this.speedQ = other.speedQ;
        this.speedR = other.speedR;
        this.currentHeat = other.currentHeat;
        this.cooling = other.cooling;
        this.ecm = other.ecm;
        this.facing = other.facing;
        this.hull = new Hull(other.hull);
        this.shield = new Shield(other.shield);
        this.image = other.image;
        this.sensorPower = other.sensorPower;
        this.sensorRange = other.sensorRange;
        this.destroyed = other.destroyed;
        this.equipment = new ArrayList<>(other.equipment);        
    }
    
    public Direction getFacing() {
        return facing;
    }
    
    public void turnLeft() {
        facing = facing.left();
    }
    
    public void turnRight() {
        facing = facing.right();
    }
    
    public Hex advance(int speed) {
        speedQ += facing.q * speed;
        speedR += facing.r * speed;
        return new Hex(facing.q*speed, facing.r*speed);
    }
    
    public Hex drift(Direction dir, int speed) {
        speedQ += dir.q*speed;
        speedR += dir.r*speed;
        return new Hex(dir.q*speed, dir.r*speed);
    }

    public void setFacing(Direction facing) {
        this.facing = facing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeedQ() {
        return speedQ;
    }

    public void setSpeedQ(int speedQ) {
        this.speedQ = speedQ;
    }

    public int getSpeedR() {
        return speedR;
    }

    public void setSpeedR(int speedR) {
        this.speedR = speedR;
    }

    @Override
    public boolean selectable() {
        return !destroyed;
    }

    @Override
    public void notifySelect(HexMap board) {
        //make sure ship panel is updated.
    }
    
    @Override
    public void startMovement(HexMap board) {
        Hex h = new Hex(speedQ, speedR);
        board.move(id, board.getLocation(id).add(h));
        for(IShipSystem system : equipment) {
            system.startMovement(board);
        }
    }

    @Override
    public void startAction(HexMap board) {
        shield.doRegen();
        for(IShipSystem system : equipment) {
            system.startAction(board);
        }
    }

    @Override
    public void endTurn(HexMap board) {
        //do nothing? heat and cooling maybe...
    }

    @Override
    public boolean drawable() {
        return true;
    }

    @Override
    public void drawItem(Graphics2D g2, Hex position, int size, int height) {
        if(destroyed) {
            return;
        }
        AffineTransform oldTransform = g2.getTransform();
        Point center = position.toPixel(2*size);
        g2.translate(center.x, center.y);
        g2.rotate(facing.theta);
        final int modSize = (size+overflow);
        final int modHeight = (height+overflow);
        g2.drawImage(image.getImage(), -modSize, -modHeight, 2*modSize, 2*modHeight, null);
        g2.setTransform(oldTransform);

    }

    @Override
    public BoardItem clone() {
        return new Ship(this);
    }

    @Override
    public String getEntityId() {
        return id;
    }
    
    @Override
    public Player getOwner() {
        return owner;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(String iconName) {
        this.imgName = iconName;
        image = new javax.swing.ImageIcon(getClass().getResource("/jessy/shipgirlcombatsystem/images/" + imgName + ".png"));
    }

    public Shield getShield() {
        return shield;
    }

    public void setShield(Shield shield) {
        this.shield = shield;
    }

    public Hull getHull() {
        return hull;
    }

    public void setHull(Hull hull) {
        this.hull = hull;
    }

    public int getCurrentHeat() {
        return currentHeat;
    }

    public void setCurrentHeat(int currentHeat) {
        this.currentHeat = currentHeat;
    }

    public int getCooling() {
        return cooling;
    }

    public void setCooling(int cooling) {
        this.cooling = cooling;
    }

    public int getEcm() {
        return ecm;
    }

    public void setEcm(int ecm) {
        this.ecm = ecm;
    }

    public int getSensorPower() {
        return sensorPower;
    }

    public void setSensorPower(int sensorPower) {
        this.sensorPower = sensorPower;
    }

    public RangeFactor getSensorRange() {
        return sensorRange;
    }

    public void setSensorRange(RangeFactor sensorRange) {
        this.sensorRange = sensorRange;
    }

    public List<IShipSystem> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<IShipSystem> equipment) {
        this.equipment = equipment;
    }
    
    public void addEquipment(IShipSystem equipment) {
        this.equipment.add(equipment);
        equipment.addToShip(this);
    }
    
    public List<IShipSystem> getWorkingEquipment(){
        List<IShipSystem> lst = new ArrayList(equipment.size());
        for(IShipSystem item : equipment) {
            if(!item.isDamaged()) {
                lst.add(item);
            }
        }
        
        return lst;
    }

    @Override
    public ThriftShip thrift() {
        ThriftShip thrift = new ThriftShip();
        thrift.setId(id);
        thrift.owner = this.owner.thrift();
        thrift.setType("Ship");
        thrift.setPosition(MapPanel.getInstance().getBoard().getLocation(id).thrift());
        
        Map<String,String> properties = new LinkedHashMap<>();
        properties.put("Facing", facing.name());
        properties.put("Name", name);
        properties.put("SpeedR", ""+speedR);
        properties.put("SpeedQ", ""+speedQ);
        properties.put("Destroyed", ""+destroyed);
        properties.put("Icon Name", imgName);
        properties.put("Cooling", ""+cooling);
        properties.put("Current Heat", ""+currentHeat);
        properties.put("ECM", ""+ecm);
        properties.put("Sensors", ""+sensorPower);
        properties.put("Sensor Range", sensorRange.name());
        
        properties.putAll(hull.thrift());
        properties.putAll(shield.thrift());
        
        thrift.setProperties(properties);
        
        for(IShipSystem item : equipment) {
            thrift.addToEquipment(item.thrift());
        }
        
        return thrift;
    }

    public void populateFromThrift(ThriftShip item) {
        Map<String, String> props = item.getProperties();
        if(props != null) {
            facing = Direction.valueOf(props.get("Facing"));
            name = props.get("Name");
            speedR = Integer.parseInt(props.get("SpeedR"));
            speedQ = Integer.parseInt(props.get("SpeedQ"));
            destroyed = Boolean.parseBoolean(props.get("Destroyed"));
            cooling = Integer.parseInt(props.get("Cooling"));
            currentHeat = Integer.parseInt(props.get("Current Heat"));
            ecm = Integer.parseInt(props.get("ECM"));
            sensorPower = Integer.parseInt(props.get("Sensors"));
            sensorRange = RangeFactor.valueOf(props.get("Sensor Range"));
            
            this.setImage(props.get("Icon Name"));

            hull = new Hull(props);
            shield = new Shield(props);
        }
        
        if(item.getEquipment() != null) {
            for(ThriftEquipment system : item.equipment) {
                switch(system.type) {
                    case "ShipWeaponSystem": equipment.add(new ShipWeaponSystem(system, this)); break;
                    default: throw new IllegalArgumentException("Unknown Ship system");
                }
            }
        }
    }

    public String applyHit(int modPower, int shieldDmg, int shieldPen, int hullDmg) {
        if(modPower <= 0) {
            return "The shot missed with power " + modPower + ".";
        }
        
        final int postShield = shield.applyHit(modPower, shieldDmg, shieldPen);
        
        String str = "The shot hit, with power " + modPower + ". ";
        if(postShield > 0) {
            str += "Punching through the shields (" + shield.currentShield + "/" + shield.maxShield + ") with remaining power " + postShield + " ";
            str += hull.applyHit(postShield, hullDmg);
        } else {
            str += "However it failed to penetrate the shields (" + shield.currentShield + "/" + shield.maxShield + ") of " + this.id + ".";
        }
        
        return str;
    }

    public static class Shield {
        int maxShield = 10;
        int shieldRegen = 2;
        int currentShield = maxShield;
                

        public Shield() {
        }
        
        public Shield(int max, int regen, int current) {
            this.maxShield = max;
            this.shieldRegen = regen;
            this.currentShield = current;
        }

        private Shield(Shield shield) {
            this(shield.maxShield, shield.shieldRegen, shield.currentShield);
        }

        private Shield(Map<String, String> map) {
            currentShield = Integer.parseInt(map.get("shield.Current"));
            maxShield = Integer.parseInt(map.get("shield.Max"));
            shieldRegen = Integer.parseInt(map.get("shield.Regen"));
        }

        private int applyHit(int power, int shieldDmg, int shieldPen) {
            this.currentShield = Math.max(this.currentShield - shieldDmg, 0);
            return power - Math.max(currentShield - shieldPen, 0);
        }

        private Map<String, String> thrift() {
            Map<String, String> map = new LinkedHashMap<String, String>();
            map.put("shield.Current", ""+currentShield);
            map.put("shield.Max", ""+maxShield);
            map.put("shield.Regen", ""+shieldRegen);
            
            return map;
        }

        private void doRegen() {
            currentShield = Math.min(currentShield + shieldRegen, maxShield);
        }
    }
    
    public String getStatusString() {
        if(destroyed) {
            return "X.X";
        }
        
        return shield.currentShield + "S/" + hull.currentDamage;
    }

    public class Hull {
        int armor = 2;
        int damage = 15;
        int destroyed = 26;
        int currentDamage  = 0;

        public Hull() {
        }
        
        public Hull(int armor, int damage, int destroyed, int currentDamage) {
            this.armor = armor;
            this.damage = damage;
            this.destroyed = destroyed;
            this.currentDamage = currentDamage;
        }

        private Hull(Hull hull) {
            this(hull.armor, hull.damage, hull.destroyed, hull.currentDamage);
        }

        private Hull(Map<String, String> map) {
            currentDamage = Integer.parseInt(map.get("hull.currentDamage"));
            damage = Integer.parseInt(map.get("hull.Damage"));
            armor = Integer.parseInt(map.get("hull.Armor"));
            destroyed = Integer.parseInt(map.get("hull.Destroyed"));
        }

        private String applyHit(int power, int hullDmg) {
            if(power <= armor) {
                return "but bouncing off the armor.";
            }
            String str = "";
            power += currentDamage;
            if(power <= damage) {
                currentDamage += hullDmg;
                str = "and doing light damage brining the damage up to " + currentDamage + ".";
            } else if(power <= destroyed) {
                currentDamage += hullDmg + 1;
                List<IShipSystem> working = getWorkingEquipment();
                if(working.isEmpty()) {
                    currentDamage += 5;
                    str = "and doing massive damage to the near wreck brining the damage up to " + currentDamage + ".";
                } else if(working.size() == 1) {
                    IShipSystem sys = working.get(0);
                    sys.setDamaged();
                    str += " destroying the last functional system " + sys.getName() + ".";
                } else {
                    int systemHit = Server.getRandomInt(working.size());
                    
                    IShipSystem sys = working.get(systemHit);
                    sys.setDamaged();
                    
                    str = "and doing significant damage brining the damage up to " + currentDamage + " and destroying system " + sys.getName() + ".";
                }
            } else {
                str = "destroying the ship outright.";
                Ship.this.destroyed = true;
            }
            return str;
        }

        private Map<String, String> thrift() {
            Map<String, String> map = new LinkedHashMap<String, String>();
            map.put("hull.currentDamage", ""+currentDamage);
            map.put("hull.Damage", ""+damage);
            map.put("hull.Destroyed", ""+destroyed);
            map.put("hull.Armor", ""+armor);
            
            return map;
        }
    }
    
}
