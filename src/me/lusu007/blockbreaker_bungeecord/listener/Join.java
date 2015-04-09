package me.lusu007.blockbreaker_bungeecord.listener;

import dev.wolveringer.BP.Player;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Created by Lukas on 06.04.2015.
 */
public class Join implements Listener {

    @EventHandler
    public void onLogin(PostLoginEvent e) {
        ProxiedPlayer pp = e.getPlayer();

        Player.getPlayer(pp.getName()).
    }
}
