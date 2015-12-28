/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.map;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import jessy.shipgirlcombatsystem.thrift.ThriftHex;
import jessy.shipgirlcombatsystem.util.MathUtil;

/**
 *
 * @author dirk
 */
public class Hex implements Cloneable {
    final int q, r;
    
    public Hex() {
        this(0,0);
    }
    
    public Hex(ThriftHex h) {
        this(h.q, h.r);
    }
    
    public Hex(Hex h) {
        this(h.q,h.r);
    }
    
    public Hex(int q, int r) {
        this.q = q;
        this.r = r;
    }
    
    public Hex(int x, int y, int z) {
        this(x,z);
        assert(x + y + z == 0);
    }
    
    public Hex(float x, float y, float z) {
        int rx = Math.round(x);
        int ry = Math.round(y);
        int rz = Math.round(z);

        float x_diff = Math.abs(rx - x);
        float y_diff = Math.abs(ry - y);
        float z_diff = Math.abs(rz - z);

        if(x_diff > y_diff && x_diff > z_diff){
            rx = -ry-rz;
        } else if(y_diff > z_diff) {
            ry = -rx-rz;
        } else {
            rz = -rx-ry;
        }
        
        q = rx;
        r = rz;
    }
    
    public int getQ() {
        return q;
    }
    
    public int getR() {
        return r;
    }
    
    public int getX() {
        return q;
    }
    
    public int getY() {
        return -q-r;
    }
    
    public int getZ() {
        return r;
    }
    
    @Override
    public Hex clone() {
        return new Hex(q,r);
    }
    
    public Hex hexTranslate(int dq, int dr) {
        return new Hex(q + dq, r + dr);
    }

    @Override
    public String toString() {
        return "{" + "q=" + q + ", r=" + r + '}';
    }
    
    public Hex add(Hex h) {
        return new Hex(h.q+q, h.r +r);
    }
    
    public static Polygon hex(int x, int y, int side, int height) {
        int[] cx = new int[]{x + side+side, x + side, x - side, x - side - side, x - side, x + side};
        int[] cy = new int[]{y, y+height, y + height, y, y - height, y - height};
        return new Polygon(cx, cy, 6);
    }
    
    public Polygon hex(int side, int height) {
        Point p = toPixel(side*2);
        return hex(p.x, p.y, side, height);
    }
    
    public int getDistance(Hex o) {
        return MathUtil.max(MathUtil.absDiff(getX(), o.getX()),
                MathUtil.absDiff(getY(), o.getY()),
                MathUtil.absDiff(getZ(), o.getZ()));
    }
    
    public List<Hex> getLine(Hex o) {
        int distance = getDistance(o);
        float cx = getX() + 0.00000001f;
        float cy = getY() + 0.00000001f;
        float cz = getZ() - 0.00000002f;
        final float dx = (o.getX() - cx) / distance;
        final float dy = (o.getY() - cy) / distance;
        final float dz = (o.getZ() - cz) / distance;
        List<Hex> retVal = new ArrayList<>(distance + 1);
        for(int i = 0; i <= distance; i++) {
            retVal.add(new Hex(cx, cy, cz));
            cx += dx; cy += dy; cz += dz;
        }
        return retVal;
    }
    
    public Set<Hex> getRing(int radious) {
        Set<Hex> result = new LinkedHashSet<>();
        Hex curHex = this.move(Direction.SOUTHWEST, radious);

        for(Direction dir : Direction.values()) {
            for(int j =0; j< radious; j++) {
                result.add(curHex);
                curHex = curHex.move(dir);
            }
        }
        return result;
    }
    
    public Point toPixel(int hexSize) {
        int x = (int) (hexSize * 1.5 * q);
        int y = (int) (hexSize * MathUtil.SQRT_3 * (r + (q/2.0f)));
        return new Point(x, y);
    }
    
    public static Hex pixelToHex(Point p, int hexSize) {
        float q = p.x * 2f/3f / hexSize;
        float r = (float) ((-p.x / 3f + MathUtil.SQRT_3/3f * p.y) / hexSize);
        return new Hex(q, -q-r, r);
    }
    
    public Hex move(Direction dir, int distance) {
        return new Hex(q + (distance*dir.q), r + (distance*dir.r));       
    }
    
    public Hex move(Direction dir) {
        return move(dir, 1);
    }

    @Override
    public int hashCode() {
        int hash;
        hash = 128 * this.q;
        hash = hash + this.r;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        //hex subtypes can be compared to hex for equality.
        if(!(obj instanceof Hex)) {
            return false;
        }
        final Hex other = (Hex) obj;
        if (this.q != other.q) {
            return false;
        }
        if (this.r != other.r) {
            return false;
        }
        return true;
    }

    public ThriftHex thrift() {
        return new ThriftHex(q,r);
    }
    
}
