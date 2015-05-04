package me.lusu007.blockbreaker_bungeecord.features;

import me.lusu007.blockbreaker_bungeecord.mysql.MySQLMethods;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Created by Lukas on 04.05.2015.
 */
public class MySQLData implements Listener {

    @EventHandler
    public void onJoin(PostLoginEvent e) {
        MySQLMethods.createUserData(e.getPlayer());
    }
}
