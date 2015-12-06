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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import jessy.shipgirlcombatsystem.map.HexMap;
import jessy.shipgirlcombatsystem.screens.MapPanel;
import jessy.shipgirlcombatsystem.util.Phase;

/**
 *
 * @author dirk
 */
public class Client implements Runnable {
    final Socket socket;
    private final ObjectOutputStream oos;
    private final ObjectInputStream ois;
    private final AtomicBoolean running = new AtomicBoolean(true);

    public Client(String hostname) throws IOException {
        this.socket = new Socket(hostname, 3333);
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
    }

    private void close() throws IOException {
        running.set(false);
        ois.close();
        oos.close();
        socket.close();
    }

    @Override
    public void run() {
        sendData(new GenericPacket(PacketType.PLAYER_JOIN, MapPanel.getInstance().getPlayerData()));
        while(running.get()) {
            try {
                Object readObject = ois.readObject();
                if(readObject instanceof Packet) {
                    final Packet p = (Packet) readObject;
                    final HexMap mapUpdate;
                    switch(p.type) {
                        case GAME_INIT:
                        case GAME_UPDATE_FIRE:
                            mapUpdate = (HexMap) p.getData();
                            MapPanel.getInstance().applyNewTurn(mapUpdate, Phase.MOVEMENT_PHASE);
                            break;
                        case GAME_UPDATE_MOVE:
                            mapUpdate = (HexMap) p.getData();
                            MapPanel.getInstance().applyNewTurn(mapUpdate, Phase.ACTION_PHASE);
                            break;
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void sendData(Packet p) {
        try {
            oos.writeUnshared(p);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
