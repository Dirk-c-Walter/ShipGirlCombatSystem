/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.ship;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import javax.swing.ImageIcon;
import jessy.shipgirlcombatsystem.map.BoardItem;
import jessy.shipgirlcombatsystem.map.Direction;
import jessy.shipgirlcombatsystem.map.Hex;
import jessy.shipgirlcombatsystem.map.HexMap;

/**
 *
 * @author dirk
 */
public class Ship implements BoardItem {
    private static final int overflow = 4;
    private final String id;
    private String name;
    private int speedQ =0;
    private int speedR =0;
    private Direction facing = Direction.NORTH;
    private ImageIcon image = new javax.swing.ImageIcon(getClass().getResource("/jessy/shipgirlcombatsystem/images/BaseShip.png"));

    public Ship(String id) {
        this.id = id;
        name= "Nautilus " + id;
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
    public void notifyRemoved(Hex a) {
        //update all components

    }

    @Override
    public void notifyAdded(Hex b) {
        //update all components

    }

    @Override
    public boolean selectable() {
        return true;
    }

    @Override
    public void notifySelect() {
        //make sure ship panel is updated.
    }

    public void move(Hex oldPos, Hex newPos) {

    }

    @Override
    public boolean drawable() {
        return true;
    }

    @Override
    public void drawItem(Graphics2D g2, Hex position, int size, int height) {
        AffineTransform oldTransform = g2.getTransform();
        Point center = position.toPixel(2*size);
        g2.translate(center.x, center.y);
        g2.rotate(facing.theta);
        final int modSize = (size+overflow);
        final int modHeight = (height+overflow);
        g2.drawImage(getImage(), -modSize, -modHeight, 2*modSize, 2*modHeight, null);
        g2.setTransform(oldTransform);

    }

    private Image getImage() {
        return image.getImage();
    }

    @Override
    public void doNewtonian() {

    }

    @Override
    public BoardItem clone() {
        return new Ship(id);
    }

    @Override
    public String getEntityId() {
        return id;
    }
    
}
