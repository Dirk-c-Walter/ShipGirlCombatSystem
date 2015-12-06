/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.net;

import jessy.shipgirlcombatsystem.map.HexMap;

/**
 *
 * @author dirk
 */
public class GameInitPacket extends Packet {
    
    private final HexMap data;

    public GameInitPacket(HexMap map) {
        super(PacketType.GAME_INIT);
        
        data = map;
    }
    
    @Override
    public HexMap getData() {
        return data;
    }
    
    
}
