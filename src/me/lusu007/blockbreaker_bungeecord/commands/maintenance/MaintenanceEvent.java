package me.lusu007.blockbreaker_bungeecord.commands.maintenance;

import me.lusu007.blockbreaker_bungeecord.mysql.MySQLMethods;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Created by Lukas on 03.04.2015.
 */
public class MaintenanceEvent implements Listener {

    @EventHandler
    public void onPing(ProxyPingEvent e) {
        if(MySQLMethods.getMaintenance() == false) {
            ServerPing conn = e.getResponse();
            conn.setDescription(MySQLMethods.getMOTD() + MySQLMethods.getSubMOTD());
            e.setResponse(conn);
        }

        if(MySQLMethods.getMaintenance() == true) {
            ServerPing conn = e.getResponse();
            conn.setVersion(new ServerPing.Protocol(ChatColor.RED + "Wartungsarbeiten", 2));
            conn.setDescription(MySQLMethods.getMOTD() + MySQLMethods.getMaintenanceSubMOTD() + MySQLMethods.getMaintenanceEnd() + "h");
            e.setResponse(conn);
        }
    }
}
