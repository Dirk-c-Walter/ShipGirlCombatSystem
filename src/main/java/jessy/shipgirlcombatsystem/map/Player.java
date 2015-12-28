/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.map;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import jessy.shipgirlcombatsystem.thrift.ThriftPlayer;
import jessy.shipgirlcombatsystem.util.ThriftUtil;


/**
 *
 * @author dirk
 */
public class Player {
    private Color playerColor;
    private String name;
    
    private static Map<String, Player> players = new HashMap<>();
    
    public Player() {
    }
    
    public static Player getPlayer(String name, Color col) {
        if(players.containsKey(name)) {
            return players.get(name);
        }
        
        return new Player(name, col);
    }
    
    public Player(String name, Color col) {
        this.name = name;
        playerColor = col;
        players.put(name, this);
    }

    public Player(ThriftPlayer player) {
        name = player.name;
        playerColor = ThriftUtil.convertThrift(player.color);
        players.put(name, this);
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        
        return Objects.equals(this.name, other.name);
    }

    public ThriftPlayer thrift() {
        return new ThriftPlayer(name, ThriftUtil.makeThrift(playerColor));
    }
    
    
}
