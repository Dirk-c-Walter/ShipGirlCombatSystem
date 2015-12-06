/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import jessy.shipgirlcombatsystem.commands.Command;
import jessy.shipgirlcombatsystem.map.HexMap;
import jessy.shipgirlcombatsystem.map.Player;
import jessy.shipgirlcombatsystem.screens.MapPanel;
import jessy.shipgirlcombatsystem.thrift.ShipGirlCombatSystemServer;
import jessy.shipgirlcombatsystem.thrift.ThriftCommandList;
import jessy.shipgirlcombatsystem.thrift.ThriftGameState;
import jessy.shipgirlcombatsystem.thrift.ThriftPacketType;
import jessy.shipgirlcombatsystem.util.Phase;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.protocol.*;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TTransportException;

/**
 *
 * @author dirk
 */
public class Client {
    private final Socket socket;
    private final ShipGirlCombatSystemServer.Client client;
    private Player me;
    
    public Client(String hostname) throws IOException, TTransportException {
        this.socket = new Socket(hostname, 3333);
        TProtocol tprot = new TBinaryProtocol(new TSocket(socket), false, true);// new TCompactProtocol(new TSocket(socket));//
        client = new ShipGirlCombatSystemServer.Client(tprot);
    }
    
    public String join(Player newPlayer) throws TException {

        me = newPlayer;
        return client.joinPlayer(newPlayer.thrift()).getMotd();
    }
    
    public HexMap doneTurn(List<Command> cmds, HexMap current, Phase phase) throws TException {
        ThriftCommandList packet = new ThriftCommandList();
        packet.setPlayer(me.thrift());
        switch(phase) {
            case INIT_PHASE: packet.setType(ThriftPacketType.DoneLobby); break;
            case MOVEMENT_PHASE: packet.setType(ThriftPacketType.DoneMove); break;
            case ACTION_PHASE: packet.setType(ThriftPacketType.DoneFire); break;
        }
        for(Command cmd: cmds) { 
            packet.addToCommands(cmd.thrift());
        }
        if(!packet.isSetCommands()) {
            packet.setCommands(Collections.EMPTY_LIST);
        }

        ThriftGameState newState = client.donePhase(packet);
        return new HexMap(newState);
    }
}
