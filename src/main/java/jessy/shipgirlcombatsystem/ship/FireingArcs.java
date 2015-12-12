/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.ship;

import java.awt.Image;
import java.util.Collections;
import java.util.EnumSet;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.ImageIcon;
import jessy.shipgirlcombatsystem.map.Direction;
import jessy.shipgirlcombatsystem.map.Hex;

/**
 *
 * @author dirk
 */
public enum FireingArcs {
    NONE("None"),
    FORWARD("Forward"),
    FORWARD3("Forward3"),
    POLE("Pole"),
    FULL("Full"),
    RIGHT("Right"),
    LEFT("Left"),
    RIGHT_FRONT("RightFront"),
    RIGHT_BACK("RightBack"),
    LEFT_FRONT("LeftFront"),
    LEFT_BACK("LeftBack"),
    POLE_BACK("PoleBack"),
    UPPER_LEFT_FRONT("UpperLeftFront"),
    UPPER_RIGHT_FRONT("UpperRihgtFront");
    
    private final ImageIcon img;
    
    private FireingArcs(String file) {
        img = new javax.swing.ImageIcon(getClass().getResource("/jessy/shipgirlcombatsystem/images/arc/" + file + ".png"));
    }
    
    public EnumSet<Direction> allowableDirections(Direction facing) {
        EnumSet set = EnumSet.noneOf(Direction.class);
        Direction temp = facing;
        switch(this) {
            case NONE: return set;
            case FORWARD: set.add(facing); return set;
            case FORWARD3: set.add(facing); set.add(facing.left()); set.add(facing.right()); return set;
            case POLE: set = EnumSet.allOf(Direction.class); set.remove(facing.opposite()); return set;
            case FULL: set = EnumSet.allOf(Direction.class); return set;
            case RIGHT: temp = facing.right(); set.add(temp); set.add(temp.right()); return set;
            case LEFT: temp = facing.left(); set.add(temp); set.add(temp.left()); return set;
            case RIGHT_FRONT: temp = facing.right(); set.add(temp); set.add(temp.right()); set.add(facing); return set;
            case RIGHT_BACK: temp = facing.right(); set.add(temp); set.add(temp.right()); set.add(facing.opposite()); return set;
            case LEFT_FRONT: temp = facing.left(); set.add(temp); set.add(temp.left()); set.add(facing); return set;
            case LEFT_BACK: temp = facing.left(); set.add(temp); set.add(temp.left()); set.add(facing.opposite()); return set;
            case POLE_BACK: set = EnumSet.allOf(Direction.class); set.remove(facing); return set;
            case UPPER_LEFT_FRONT: set.add(facing); set.add(facing.left()); return set;
            case UPPER_RIGHT_FRONT: set.add(facing); set.add(facing.right()); return set;
        }
        return set;
    }
    
    public Set<Hex> allowableFirstHex(Direction facing, Hex origin) {
        EnumSet<Direction> dirs = this.allowableDirections(facing);
        Set<Hex> set = new LinkedHashSet<>();
        for(Direction dir : dirs) {
            set.add(origin.move(dir));
        }
        return set;
    }
    
    public boolean canHitSameHex() {
        return this == NONE || this == FULL;
    }
}
