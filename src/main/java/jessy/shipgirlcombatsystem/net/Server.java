/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import jessy.shipgirlcombatsystem.commands.Command;
import jessy.shipgirlcombatsystem.map.HexMap;
import jessy.shipgirlcombatsystem.util.Phase;
import static jessy.shipgirlcombatsystem.util.Phase.MOVEMENT_PHASE;

/**
 *
 * @author dirk
 */
public class Server {
    private boolean running = true;
    
    private ServerSocket socket;
    private final List<ServerClient> clients = Collections.synchronizedList(new ArrayList<ServerClient>());
    private HexMap gameState = new HexMap(15);
    private Set<ServerClient> waitingOn = Collections.synchronizedSet(new HashSet<ServerClient>());
    private Phase currentPhase = Phase.INIT_PHASE;
    
    public Server(int port) throws IOException {
        this.socket = new ServerSocket(port);
    }
    
    public void start() {
        while(running) {
            try {
                ServerClient newClient = new ServerClient(socket.accept());
                clients.add(newClient);
                newClient.sendData(new GameInitPacket(new HexMap(gameState)));
                (new Thread(newClient)).start();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void stop() throws IOException {
        running = false;
        for(ServerClient s : clients) {
            try {
                s.close();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void serverRecieve(ServerClient client, Packet packet) {
        switch(packet.type) {
            case END_MOVE:
                serverEndMove(client, (List<Command>) packet.getData()); break;
            case END_FIRE:
                serverEndFire(client, (List<Command>) packet.getData()); break;
            case END_LOBBY:
                serverStartGame(client, (HexMap) packet.getData()); break;
        }
    }

    private void serverEndMove(ServerClient client, List<Command> list) {
        waitingOn.remove(client);
        for(Command c : list) {
            c.applyCommand(gameState);
        }
        if(waitingOn.isEmpty()) {
            sendMapToAllClients();
        }
                
    }

    private void serverEndFire(ServerClient client, List<Command> list) {
        waitingOn.remove(client);
        for(Command c : list) {
            c.applyCommand(gameState);
        }
        if(waitingOn.isEmpty()) {
            sendMapToAllClients();
        }
    }

    private void serverStartGame(ServerClient client, HexMap hexMap) {
        assert(currentPhase == Phase.INIT_PHASE);
        
        gameState = hexMap;
        currentPhase = MOVEMENT_PHASE;
        sendMapToAllClients();
    }

    private void sendMapToAllClients() {
        gameState.setPhase(currentPhase);
        waitingOn.clear();
        waitingOn.addAll(clients);
        final HexMap update= new HexMap(gameState);
        for(ServerClient c : clients) {
            c.sendData(new GenericPacket(PacketType.GAME_UPDATE_MOVE, update));
        }
    }

    private class ServerClient implements Runnable {
        final Socket socket;
        private final ObjectOutputStream oos;
        private final ObjectInputStream ois;
        private final AtomicBoolean running = new AtomicBoolean(true);

        public ServerClient(Socket socket) throws IOException {
            this.socket = socket;
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
            while(running.get()) {
                try {
                    Object readObject = ois.readObject();
                    if(readObject instanceof Packet) {
                        serverRecieve(this, (Packet) readObject);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        private void sendData(Packet p) {
            try {
                oos.writeUnshared(p);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
