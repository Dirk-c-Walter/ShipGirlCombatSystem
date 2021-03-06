/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.net;

import java.awt.Color;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import jessy.shipgirlcombatsystem.commands.Command;
import jessy.shipgirlcombatsystem.commands.ShipCommands;
import jessy.shipgirlcombatsystem.map.Direction;
import jessy.shipgirlcombatsystem.map.Hex;
import jessy.shipgirlcombatsystem.map.HexMap;
import jessy.shipgirlcombatsystem.map.Player;
import jessy.shipgirlcombatsystem.screens.MapPanel;
import jessy.shipgirlcombatsystem.ship.Ship;
import jessy.shipgirlcombatsystem.ship.systems.ShipWeaponSystem;
import jessy.shipgirlcombatsystem.thrift.ShipGirlCombatSystemServer;
import jessy.shipgirlcombatsystem.thrift.ShipGirlServiceError;
import jessy.shipgirlcombatsystem.thrift.ThriftCommand;
import jessy.shipgirlcombatsystem.thrift.ThriftCommandList;
import jessy.shipgirlcombatsystem.thrift.ThriftGameState;
import static jessy.shipgirlcombatsystem.thrift.ThriftPacketType.*;
import jessy.shipgirlcombatsystem.thrift.ThriftPlayer;
import jessy.shipgirlcombatsystem.thrift.ThriftWelcome;
import jessy.shipgirlcombatsystem.util.Phase;
import jessy.shipgirlcombatsystem.util.ThriftUtil;
import org.apache.thrift.TException;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 *
 * @author dirk
 */
public class Server {
    private TThreadPoolServer server;
    private ServerSocket socket;
    private HexMap gameState = new HexMap(20);
    private Phase currentPhase = Phase.INIT_PHASE;
    private final GameProcessor processor = new GameProcessor();
    private int turn = 0;
    private List<ThriftCommand> turnActions = Collections.synchronizedList(new LinkedList<ThriftCommand>());
    private final MapPanel panel;
    private static final Random rand = new Random();
    
    public static void main(String[] args) throws IOException, TTransportException {
        Server server1 = new Server(3300);
    }
    
    public static int getRandomInt(int bound) {
        return rand.nextInt(bound);
    }
    
    public static int getRandomMod() {
        float mod = (float) (rand.nextGaussian() * 5.0);
        mod = Math.min(mod, 10);
        mod = Math.max(mod, -10);
        return Math.round(mod);
    }
   
    public Server(int port) throws IOException, TTransportException {
        this.socket = new ServerSocket(port);
        panel = new MapPanel();
        
        Ship redShip = new Ship(gameState.getNewUniqueID(), new Player("A", Color.pink));
        redShip.addEquipment(new ShipWeaponSystem("Big Gun", redShip));
        redShip.addEquipment(new ShipWeaponSystem("Small Gun", redShip));
        gameState.add(redShip, new Hex(4,4));
        Ship blueShip = new Ship(gameState.getNewUniqueID(), new Player("D",  new Color(50,50,255)));
        blueShip.addEquipment(new ShipWeaponSystem("Big Gun", blueShip));
        blueShip.addEquipment(new ShipWeaponSystem("Small Gun", blueShip));
        blueShip.setFacing(Direction.SOUTH);
        gameState.add(blueShip, new Hex(-4,-4));
        
        //gameState.add(new Ship(gameState.getNewUniqueID(), new Player("B", new Color(50,255,50))), new Hex(2,-2));
        //gameState.add(new Ship(gameState.getNewUniqueID(), new Player("C", Color.YELLOW)), new Hex(-2,2));
        
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
    
    public synchronized ThriftGameState packageGameState() {
        applyCommands();
        ThriftGameState state = new ThriftGameState();
        state.setMapRadious(gameState.getRadious());
        state.setTurn(turn);
        state.setItems(ThriftUtil.makeThrift(gameState));
        state.messages = gameState.getCommandLog();
        
        currentPhase = gameState.getPhase();
        state.setPhaseCode(currentPhase.ordinal());
        return state;
    }
    
    public synchronized ThriftGameState packageCurrentGameState() {
        ThriftGameState state = new ThriftGameState();
        state.setMapRadious(gameState.getRadious());
        state.setTurn(turn);
        state.setItems(ThriftUtil.makeThrift(gameState));
        
        state.setPhaseCode(currentPhase.ordinal());
        return state;
    }

    private void applyCommands() {
        turn++;
        final List<Command> output = new ArrayList<Command>();
        for(ThriftCommand cmd : turnActions) {
            final Command c;
            switch(cmd.type) {
                case "Enum" :
                    c = ShipCommands.fromEnum(cmd.commandCode, cmd);
                    break;
                case "FireShipWeapon": 
                    c = new ShipCommands.FireWeaponCommand(cmd);
                    break;
                default: c = null;
            } 
            c.applyCommand(gameState);
            System.out.println("Doing: " + c.toString());
            output.add(c);
        }
        gameState.advancePhase();
        for(Command out : output){
            gameState.logCommand(out.toString());
        }
        currentPhase = gameState.getPhase();
        System.out.println("Advancing to turn: " + turn + " and phase: " + currentPhase);
        try {
            panel.applyNewTurnAndWait(gameState, currentPhase);
        } catch (InterruptedException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                final ThriftWelcome ret = new ThriftWelcome("Welcome back.");
                ret.setState(packageCurrentGameState());
                return ret;
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
