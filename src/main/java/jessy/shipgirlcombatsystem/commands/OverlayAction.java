
package jessy.shipgirlcombatsystem.commands;

import java.awt.Color;
import java.util.List;
import javax.swing.JPopupMenu;
import jessy.shipgirlcombatsystem.map.BoardItem;
import jessy.shipgirlcombatsystem.map.Hex;
import jessy.shipgirlcombatsystem.map.HexMap;

/**
 *
 * @author dirk
 */
public abstract class OverlayAction {

 
    public abstract JPopupMenu getMenuForHex(Hex a, HexMap current, BoardItem[] list);

    public String getTextForHex(int distance) {
        return "" + distance;
    }

    public abstract Color getColorFor(List<Hex> line, int distance);
}
