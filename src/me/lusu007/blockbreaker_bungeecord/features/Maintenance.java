package me.lusu007.blockbreaker_bungeecord.features;

import me.lusu007.blockbreaker_bungeecord.BungeeCordMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Created by Lukas on 07.05.2015.
 */
public class Maintenance extends Command implements Listener {

    public static String reason;
    public static boolean maintenance;

    public Maintenance(String name) {
        super(name);
    }

    @EventHandler
    public void ProxyPingEvent(ProxyPingEvent e) {
        if(maintenance == true) {
            ServerPing response = e.getResponse();

            response.setPlayers(new ServerPing.Players(0, 0, new ServerPing.PlayerInfo[0]));
            response.setDescription(ChatColor.AQUA + "BlockBreaker" + ChatColor.GREEN + ".de" + ChatColor.DARK_GRAY + " - " + ChatColor.DARK_RED + "Wartungsmodus");
            response.setVersion(new ServerPing.Protocol(ChatColor.DARK_RED + "Wartungsarbeiten" + ChatColor.DARK_GRAY + "/" + ChatColor.DARK_RED + reason, 2));
        }
    }

    @EventHandler
    public void onLogin(PostLoginEvent e) {
        ProxiedPlayer pp = e.getPlayer();

        if(maintenance == true) {
            if(!pp.hasPermission("server.maintenance.join")) {
                pp.disconnect(new TextComponent(ChatColor.DARK_RED + "Wir sind momentan in den Wartungsarbeiten und sind bald wieder für euch da! " + ChatColor.DARK_AQUA + ":-)"));
            }
        }
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!sender.hasPermission("server.maintenance.set")) return;

        if(args.length == 0) {
            if(maintenance == false) {
                BungeeCordMain.getMaintenanceCfg().set("maintenance", true);
                BungeeCordMain.getMaintenanceCfg().set("reason", "Wartung");

                maintenance = BungeeCordMain.getMaintenanceCfg().getBoolean("maintenance");
                reason = BungeeCordMain.getMaintenanceCfg().getString("reason");
            }

            if(maintenance == true) {
                BungeeCordMain.getMaintenanceCfg().set("maintenance", false);
                BungeeCordMain.getMaintenanceCfg().set("reason", "Wartung");

                maintenance = BungeeCordMain.getMaintenanceCfg().getBoolean("maintenance");
                reason = BungeeCordMain.getMaintenanceCfg().getString("reason");
            }
        }

        if(args.length >= 1) {
            String reasonstring = "";

            for (int i = 0; i < args.length; i++) {
                reasonstring = reasonstring + args[i] + " ";
            }

            BungeeCordMain.getMaintenanceCfg().set("reason", reasonstring);

            if(maintenance == false) {
                BungeeCordMain.getMaintenanceCfg().set("maintenance", true);
                BungeeCordMain.getMaintenanceCfg().set("reason", "Wartung");

                maintenance = BungeeCordMain.getMaintenanceCfg().getBoolean("maintenance");
                reason = BungeeCordMain.getMaintenanceCfg().getString("reason");
            }

            if(maintenance == true) {
                BungeeCordMain.getMaintenanceCfg().set("maintenance", false);
                BungeeCordMain.getMaintenanceCfg().set("reason", "Wartung");

                maintenance = BungeeCordMain.getMaintenanceCfg().getBoolean("maintenance");
                reason = BungeeCordMain.getMaintenanceCfg().getString("reason");
            }

        }


    }


}
