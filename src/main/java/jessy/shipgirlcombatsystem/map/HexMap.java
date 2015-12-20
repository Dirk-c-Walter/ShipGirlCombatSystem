/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.map;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import jessy.shipgirlcombatsystem.commands.ServerCommand;
import jessy.shipgirlcombatsystem.screens.MapPanel;
import jessy.shipgirlcombatsystem.ship.Ship;
import jessy.shipgirlcombatsystem.thrift.ThriftGameState;
import jessy.shipgirlcombatsystem.thrift.ThriftShip;
import jessy.shipgirlcombatsystem.util.Phase;
import static jessy.shipgirlcombatsystem.util.Phase.*;
import jessy.shipgirlcombatsystem.util.ThriftUtil;

/**
 *
 * @author dirk
 */
public class HexMap {
    private final int radious;
    
    private Map<Hex, LinkedHashSet<String>> board = Collections.synchronizedMap(new LinkedHashMap<Hex, LinkedHashSet<String>>());
    private Map<String, BoardItem> itemList = Collections.synchronizedMap(new LinkedHashMap<String, BoardItem>());
    private Map<String, Hex> itemLocation = Collections.synchronizedMap(new LinkedHashMap<String, Hex>());
    private static final AtomicLong uniqueidCounter = new AtomicLong(0);
    
    private Player player = null;
    private Phase phase = INIT_PHASE;
    private List<ServerCommand> serverCommands = new ArrayList<>();
    private List<String> log = new LinkedList<>();
    
    public HexMap(int size) {
        radious = size;
    }
    
    public HexMap(HexMap other) {
        radious = other.radious;
        phase = other.phase;
        player = other.player;
        
        board = Collections.synchronizedMap(new LinkedHashMap<>(other.board));
        itemList = Collections.synchronizedMap(new LinkedHashMap<String, BoardItem>(other.itemList));
        itemLocation = Collections.synchronizedMap(new LinkedHashMap<String, Hex>(other.itemLocation));

    }

    public HexMap(ThriftGameState newState) {
        radious = newState.mapRadious;
        player = MapPanel.getInstance().getPlayerData();
        phase = Phase.values()[newState.phaseCode];

        for(ThriftShip item : newState.getItems()) {
            BoardItem val = ThriftUtil.convertThrift(item);
            if(val != null) {
                add(val, new Hex(item.position));
            }
        }
    }
    
    public String getNewUniqueID() {
        return "E:"+ uniqueidCounter.incrementAndGet();
    }
    
    public void add(BoardItem item, Hex loc) {
        assert(!itemList.containsKey(item.getEntityId()));
        
        itemList.put(item.getEntityId(), item);
        LinkedHashSet<String> set = board.get(loc);
        if(set == null) {
            set = new LinkedHashSet<>();
        }
        set.add(item.getEntityId());
        board.put(loc, set);
        itemLocation.put(item.getEntityId(), loc);
    }
    
    public void move(String id, Hex target) {
        Hex loc = itemLocation.get(id);
        board.get(loc).remove(id);
        itemLocation.put(id, target);
        LinkedHashSet<String> set = board.get(target);
        if(set == null) {
            set = new LinkedHashSet<>();
            board.put(target, set);
        }
        set.add(id);
    }
    
    public int getRadious() {
        return radious;
    }
    
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }
    
    public Collection<BoardItem> getAllItems() {
        return Collections.unmodifiableCollection(itemList.values());
    }
   
    public boolean inBounds(Hex a) {
        if(Math.abs(a.getQ()) > radious) {
            return false;
        }
        if(Math.abs(a.getR()) > radious) {
            return false;
        }
        return true;
    }
    
    public Set<String> getEntityIdsAt(Hex h) {
        assert(h!=null);
        Set<String> i = board.get(h);
        if(i == null || i.isEmpty()) {
            return Collections.EMPTY_SET;
        }
        
        //return a fresh copy to avoid concurent modifications.
        return new LinkedHashSet<>(i);
    }
    
    public Set<BoardItem> getEntitiesAt(Hex h) {
        assert(h!=null);
        Set<String> i = board.get(h);
        if(i == null || i.isEmpty()) {
            return Collections.EMPTY_SET;
        }
        Set<BoardItem> retVal = new LinkedHashSet<>(i.size());
        for(String id : i) {
            retVal.add(itemList.get(id));
        }
        
        return retVal;
    }
    
    public BoardItem getEntity(String id) {
        assert(id != null);
        return itemList.get(id);
    }
    
    public Hex getLocation(String id) {
        return itemLocation.get(id);
    }

    public Color getColorHex(Hex h) {
        final LinkedHashSet<String> s = board.get(h);
        if(s != null && !s.isEmpty()) {
            if(s.size() == 1) {
                return getEntity(s.toArray()[0].toString()).getOwner().getPlayerColor();
            } else {
                return Color.lightGray;
            }    
        }
        return Color.BLACK;
    }

    //Server Only!
    public void advancePhase() {
        log.clear();
        if(phase == MOVEMENT_PHASE) {
            phase = ACTION_PHASE;
            for(BoardItem ship : itemList.values() ) {
                ship.startAction(this);
            }
        } else {
            for(ServerCommand cmd : serverCommands) {
                cmd.endActionPhase(this);
            }
            phase = MOVEMENT_PHASE;
            for(BoardItem ship : itemList.values() ) {
                ship.startMovement(this);
            }
        }
        
        serverCommands.clear();
    }

    public void addServerCommand(ServerCommand serverCommand) {
        serverCommands.add(serverCommand);
    }

    public void logCommand(String cmd) {
        log.add(cmd);
    }
    
    public List<String> getCommandLog() {
        return log;
    }

  
}
