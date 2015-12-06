/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.util;

import java.awt.Color;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import jessy.shipgirlcombatsystem.map.BoardItem;
import jessy.shipgirlcombatsystem.map.HexMap;
import jessy.shipgirlcombatsystem.map.Player;
import jessy.shipgirlcombatsystem.ship.Ship;
import jessy.shipgirlcombatsystem.thrift.ThriftColor;
import jessy.shipgirlcombatsystem.thrift.ThriftShip;

/**
 *
 * @author dirk
 */
public class ThriftUtil {
    public static Color convertThrift(ThriftColor col) {
        return new Color(col.red, col.green, col.green);
    }
    
    public static ThriftColor makeThrift(Color col) {
        return new ThriftColor(col.getRed(), col.getGreen(), col.getBlue());
    }

    public static List<ThriftShip> makeThrift(HexMap gameState) {
        List<ThriftShip> list = new LinkedList<>();
        for(BoardItem item : gameState.getAllItems()) {
            ThriftShip thrift = item.thrift();
            if(thrift != null) {
                list.add(thrift);
            }
        }
        return list;
    }

    public static void invokeLater(Runnable runnable) {
        new Thread(runnable).start();
    }

    public static BoardItem convertThrift(ThriftShip item) {
        if("Ship".equals(item.type)) {
            Ship ship = new Ship(item.id, new Player(item.owner));
            
            ship.populateFromThrift(item);
            
            return ship;
        }
        
        return null;
    }
}
