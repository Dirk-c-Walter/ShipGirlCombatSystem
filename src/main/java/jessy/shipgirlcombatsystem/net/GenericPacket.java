/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.net;

/**
 *
 * @author dirk
 */
public class GenericPacket extends Packet {
    private final Object data;

    public GenericPacket(PacketType type, Object data) {
        super(type);
        this.data = data;
    }

    @Override
    public Object getData() {
        return data;
    }
    
}
