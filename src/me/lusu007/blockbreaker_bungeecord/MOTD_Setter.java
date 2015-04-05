package me.lusu007.blockbreaker_bungeecord;

import me.lusu007.blockbreaker_bungeecord.mysql.MySQLMethods;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Created by Lukas on 03.04.2015.
 */
public class MOTD_Setter implements Listener {

    public MOTD_Setter(BungeeCordMain plugin) {
        ProxyServer.getInstance().getPluginManager().registerListener(plugin, this);
    }

    @EventHandler
    public void onPing(ProxyPingEvent e) {
        if(ServerPinger.maintenance == false) {
            ServerPing conn = e.getResponse();
            //conn.setVersion(new ServerPing.Protocol(ChatColor.RED + "Wartungsarbeiten", 2));
            conn.setDescription(MySQLMethods.getMOTD() + MySQLMethods.getSubMOTD());
            e.setResponse(conn);
        }

        if(ServerPinger.maintenance == true) {
            ServerPing conn = e.getResponse();
            conn.setVersion(new ServerPing.Protocol(ChatColor.RED + "Wartungsarbeiten", 2));
            conn.setDescription("§bPortal§6MC §8- §cWartungsmodus");
            e.setResponse(conn);
        }
    }
}
