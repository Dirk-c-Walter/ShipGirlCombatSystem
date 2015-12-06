/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.screens;

import javax.swing.JPanel;

/**
 *
 * @author dirk
 */
public class PanelFrame extends javax.swing.JFrame {
    
    protected JPanel panel;
    
    public PanelFrame(JPanel panel) {
        super();
        setSize(600, 350);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout());
        getContentPane().add(panel);
        this.panel = panel;
    }
}
