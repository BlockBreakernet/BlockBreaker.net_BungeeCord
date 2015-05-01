package me.lusu007.blockbreaker_bungeecord.features;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Created by Lukas on 30.04.2015.
 */
public class AutoReconnect implements Listener {

    @EventHandler
    public void onKick(ServerKickEvent e) {
        e.getPlayer().connect(ProxyServer.getInstance().getServerInfo("lobby"));
    }
}
