/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.map;

/**
 *
 * @author dirk
 */
public enum Direction {
    NORTH(0,-1),
    NORTHWEST(1,-1),
    SOUTHWEST(1,0),
    SOUTH(0,1),
    SOUTHEAST(-1,1),
    NORTHEAST(-1,0);
    
    public final int q;
    public final int r;
    public final double theta; //rotation in radians
    
    private Direction(int x, int y) {
        this.q = x;
        this.r = y;
        theta = this.ordinal()* Math.PI/3.0d; 
   }

    public Direction left() {
        switch(this) {
            case NORTH: return NORTHEAST;
            case NORTHWEST: return NORTH;
            case SOUTHWEST: return NORTHWEST;
            case SOUTH: return SOUTHWEST;    
            case SOUTHEAST: return SOUTH;
            case NORTHEAST: return SOUTHEAST;    
        }
        return null;
    }

    public Direction right() {
        switch(this) {
            case NORTH: return NORTHWEST;
            case NORTHWEST: return SOUTHWEST;
            case SOUTHWEST: return SOUTH;
            case SOUTH: return SOUTHEAST;    
            case SOUTHEAST: return NORTHEAST;
            case NORTHEAST: return NORTH;    
        }
        return null;
    }
    
    
}
