/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.util;

import java.util.Arrays;

/**
 *
 * @author dirk
 */
public class MathUtil {
    public static final double SQRT_3 = Math.sqrt(3);
    
    public static int max(int... a) {
        if(a == null || a.length ==0) {
            return 0;
        } else if(a.length == 1) {
            return a[0];
        }
        Arrays.sort(a);
        return a[a.length-1];
    }
    
    public static int absDiff(int a, int b) {
        return Math.abs(a - b);
    }
}
