/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.net;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import jessy.shipgirlcombatsystem.commands.Command;
import jessy.shipgirlcombatsystem.commands.ShipCommands;
import jessy.shipgirlcombatsystem.map.Hex;
import jessy.shipgirlcombatsystem.map.HexMap;
import jessy.shipgirlcombatsystem.map.Player;
import jessy.shipgirlcombatsystem.screens.MapPanel;
import jessy.shipgirlcombatsystem.ship.Ship;
import jessy.shipgirlcombatsystem.thrift.ShipGirlCombatSystemServer;
import jessy.shipgirlcombatsystem.thrift.ShipGirlServiceError;
import jessy.shipgirlcombatsystem.thrift.ThriftCommand;
import jessy.shipgirlcombatsystem.thrift.ThriftCommandList;
import jessy.shipgirlcombatsystem.thrift.ThriftGameState;
import static jessy.shipgirlcombatsystem.thrift.ThriftPacketType.*;
import jessy.shipgirlcombatsystem.thrift.ThriftPlayer;
import jessy.shipgirlcombatsystem.thrift.ThriftWelcome;
import jessy.shipgirlcombatsystem.util.Phase;
import static jessy.shipgirlcombatsystem.util.Phase.MOVEMENT_PHASE;
import jessy.shipgirlcombatsystem.util.ThriftUtil;
import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;

/**
 *
 * @author dirk
 */
public class Server {
    private TThreadPoolServer server;
    private ServerSocket socket;
    private HexMap gameState = new HexMap(15);
    private Phase currentPhase = Phase.INIT_PHASE;
    private final GameProcessor processor = new GameProcessor();
    private int turn = 0;
    private List<ThriftCommand> turnActions = Collections.synchronizedList(new LinkedList<ThriftCommand>());
    private final MapPanel panel;
    
    public static void main(String[] args) throws IOException, TTransportException {
        Server server1 = new Server(3300);
    }
    
    public Server(int port) throws IOException, TTransportException {
        this.socket = new ServerSocket(port);
        panel = new MapPanel();
        
        gameState.add(new Ship(gameState.getNewUniqueID(), new Player("A", Color.pink)), new Hex(2,2));
        gameState.add(new Ship(gameState.getNewUniqueID(), new Player("B", new Color(50,255,50))), new Hex(2,-2));
        gameState.add(new Ship(gameState.getNewUniqueID(), new Player("C", Color.YELLOW)), new Hex(-2,2));
        gameState.add(new Ship(gameState.getNewUniqueID(), new Player("D",  new Color(50,50,255))), new Hex(-2,-2));
        
        panel.applyNewTurn(gameState, currentPhase);
        
        TServerSocket tsock = new TServerSocket(socket);
        TThreadPoolServer.Args args = new TThreadPoolServer.Args(tsock);
        args.requestTimeout(2);
        args.requestTimeoutUnit(TimeUnit.HOURS);
        args.processor(new ShipGirlCombatSystemServer.Processor<>(processor));
        server = new TThreadPoolServer(args);
        server.serve();
    }
    
    public void addCommands(List<ThriftCommand> commands) {
        if(commands != null) {
            turnActions.addAll(commands);
        }
    }
    
    public ThriftGameState packageGameState() {
        applyCommands();
        ThriftGameState state = new ThriftGameState();
        state.setMapRadious(gameState.getRadious());
        state.setTurn(turn);
        state.setItems(ThriftUtil.makeThrift(gameState));
        
        currentPhase = gameState.getPhase();
        state.setPhaseCode(currentPhase.ordinal());
        return state;
    }

    private void applyCommands() {
        turn++;
        for(ThriftCommand cmd : turnActions) {
            if("Enum".equals(cmd.type)) {
                Command c = ShipCommands.fromEnum(cmd.commandCode, cmd);
                c.applyCommand(gameState);
            }
        }
        gameState.advancePhase();
        panel.applyNewTurn(gameState, currentPhase);
        processor.waiter = new WaitObject(processor.playerList);
        turnActions = Collections.synchronizedList(new LinkedList<ThriftCommand>());
    }

    private class GameProcessor implements ShipGirlCombatSystemServer.Iface {
        private Map<String, Player> playerList = Collections.synchronizedMap(new LinkedHashMap<String, Player>());
        private WaitObject waiter = new WaitObject(playerList);

        @Override
        public ThriftWelcome joinPlayer(ThriftPlayer player) throws ShipGirlServiceError, TException {
            System.out.println("Player " + player.name + " joined.");
            if(playerList.containsKey(player.name)) {
                return new ThriftWelcome("Welcome back.");
            }
            
            playerList.put(player.name, new Player(player));
            waiter.add(player.name);
            return new ThriftWelcome("Glad you could join us.");
        }

        @Override
        public ThriftGameState donePhase(ThriftCommandList commands) throws ShipGirlServiceError, TException {
            addCommands(commands.commands);
            System.out.println("Player " + commands.player.name + " is done with " + commands.type.toString());
            switch(commands.type) {
                case DoneLobby:
                case DoneMove:
                case DoneFire:
            }

            return waiter.waitTillDone(commands.player.name);
        }

    }
    
    private class WaitObject {
        private final Set<String> waitingOn = Collections.synchronizedSet(new LinkedHashSet<String>());
        private ThriftGameState game;
        
        private WaitObject(Map<String, Player> playerList) {
            waitingOn.addAll(playerList.keySet());
        }

        private synchronized void add(String name) {
            waitingOn.add(name);
        }
        
        private synchronized ThriftGameState waitTillDone(String name) {
            waitingOn.remove(name);
            if(waitingOn.isEmpty()) {
                wakeAll(packageGameState());
            }
            while(game == null) {
                try {
                    wait();
                } catch (InterruptedException e) {}
            }
            
            return game;
        }
        
        private synchronized void wakeAll(ThriftGameState game) {
            this.game = game;
            this.notifyAll();
        }
    }
}
