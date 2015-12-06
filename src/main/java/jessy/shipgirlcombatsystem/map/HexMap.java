/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.map;

import java.awt.Color;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import jessy.shipgirlcombatsystem.ship.Ship;
import jessy.shipgirlcombatsystem.util.Phase;
import static jessy.shipgirlcombatsystem.util.Phase.INIT_PHASE;
import static jessy.shipgirlcombatsystem.util.Phase.WAIT_PHASE;

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
    private Phase phase = WAIT_PHASE;
    
    public HexMap(int size) {
        radious = size;

    }
    
    public HexMap(HexMap other) {
        radious = other.radious;
        
        board = Collections.synchronizedMap(new LinkedHashMap<>(other.board));
        itemList = Collections.synchronizedMap(new LinkedHashMap<String, BoardItem>(other.itemList.size()));
        itemLocation = Collections.synchronizedMap(new LinkedHashMap<String, Hex>(other.itemLocation));
        //itemlist needs a deep copy.
        for(Map.Entry<String, BoardItem> i : other.itemList.entrySet()) {
            itemList.put(i.getKey(), i.getValue().clone());
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
                return Color.PINK;
            } else {
                return Color.RED;
            }    
        }
        return Color.BLACK;
    }

  
}
