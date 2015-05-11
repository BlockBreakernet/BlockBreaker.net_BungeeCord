package me.lusu007.blockbreaker_bungeecord.features;

import me.lusu007.blockbreaker_bungeecord.mysql.MySQLMethods;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Created by Lukas on 11.05.2015.
 */
public class ProxyPing implements Listener {

    @EventHandler
    public void onPing(ProxyPingEvent e) {
        ServerPing conn = e.getResponse();

        conn.setDescription(MySQLMethods.getMOTD() + MySQLMethods.getSubMOTD());

        e.setResponse(conn);
    }
}
