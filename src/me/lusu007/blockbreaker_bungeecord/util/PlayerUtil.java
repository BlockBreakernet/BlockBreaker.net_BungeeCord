package me.lusu007.blockbreaker_bungeecord.util;

import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * Created by Lukas on 27.05.2015.
 */
public class PlayerUtil {

    public static String getUUID(ProxiedPlayer playername) {
        return playername.getUUID();
    }
}
