package me.lusu007.blockbreaker_bungeecord.listener;

import me.lusu007.blockbreaker_bungeecord.BungeeCordMain;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Created by Lukas on 06.04.2015.
 */
public class Join implements Listener {

    public Join(BungeeCordMain plugin) {
        ProxyServer.getInstance().getPluginManager().registerListener(plugin, this);
    }

    @EventHandler
    public void onPing(PostLoginEvent e) {
        ProxiedPlayer pp = e.getPlayer();

        if(ProxyServer.getInstance().getServerInfo("lobby1").getPlayers().size() == 10) {

        }

    }
}
