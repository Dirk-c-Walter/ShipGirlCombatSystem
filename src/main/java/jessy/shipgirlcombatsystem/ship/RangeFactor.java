/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.ship;

/**
 *
 * @author dirk
 */
public enum RangeFactor {
    DIV4,
    DIV3,
    DIV2,
    DISTANCE,
    MULT2,
    MULT3,
    MULT4,
    MULT5,
    MULT10,
    MELEE;

    public int modPower(int power, int distance) {
        switch(this) {
            case DISTANCE: return power - distance;
            case DIV2: return power - (distance/2);
            case DIV3: return power - (distance/3);
            case DIV4: return power - (distance/4);
            case MULT2: return power - (distance*2);
            case MULT3: return power - (distance*3);
            case MULT4: return power - (distance*4);
            case MULT5: return power - (distance*5);
            case MULT10: return power - (distance*10);
            case MELEE: return distance == 0 ? power : Integer.MIN_VALUE;
        }
        return Integer.MIN_VALUE;
    }
}
