/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jessy.shipgirlcombatsystem.net;

/**
 *
 * @author dirk
 */
public enum PacketType {
    OTHER,
    GAME_INIT,
    END_LOBBY,
    END_MOVE,
    GAME_UPDATE_MOVE,
    END_FIRE,
    GAME_UPDATE_FIRE,
    PLAYER_JOIN;
}
