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
public abstract class Packet<T> {
    public final PacketType type;
    
    public Packet(PacketType type) {
        this.type = type;
    }
    
    public abstract T getData();
}
