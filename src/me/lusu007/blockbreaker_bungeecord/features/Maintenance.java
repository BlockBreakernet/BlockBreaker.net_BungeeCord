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
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.event.EventHandler;

import java.io.IOException;

/**
 * Created by Lukas on 07.05.2015.
 */
public class Maintenance extends Command implements Listener {

    public static String reason;
    public static boolean maintenance;

    private final Plugin plugin;

    public Maintenance(Plugin plugin) {
        super("wartung");
        this.plugin = plugin;
    }

    @EventHandler
    public void ProxyPingEvent(ProxyPingEvent e) {
        if(maintenance == true) {
            ServerPing response = e.getResponse();

            response.setPlayers(new ServerPing.Players(0, 0, new ServerPing.PlayerInfo[0]));
            response.setDescription(ChatColor.AQUA + "BlockBreaker" + ChatColor.GREEN + ".de" + ChatColor.DARK_GRAY + " - " + ChatColor.DARK_RED + "Wartungsmodus");
            response.setVersion(new ServerPing.Protocol(ChatColor.DARK_RED + "Wartungsarbeiten" + ChatColor.DARK_GRAY + "/" + ChatColor.DARK_RED + reason, 2));

            e.setResponse(response);
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

                try {
                    ConfigurationProvider.getProvider(YamlConfiguration.class).save(BungeeCordMain.getMaintenanceCfg(), BungeeCordMain.getMaintenanceFile());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                maintenance = BungeeCordMain.getMaintenanceCfg().getBoolean("maintenance");
                reason = BungeeCordMain.getMaintenanceCfg().getString("reason");

                sender.sendMessage(new TextComponent(ChatColor.DARK_RED + "Du hast das Netzwerk in den " + ChatColor.DARK_AQUA + "Wartungsmodus " + ChatColor.GOLD + "gesetzt!"));
                return;
            }

            if(maintenance == true) {
                BungeeCordMain.getMaintenanceCfg().set("maintenance", false);
                BungeeCordMain.getMaintenanceCfg().set("reason", "Wartung");

                try {
                    ConfigurationProvider.getProvider(YamlConfiguration.class).save(BungeeCordMain.getMaintenanceCfg(), BungeeCordMain.getMaintenanceFile());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                maintenance = BungeeCordMain.getMaintenanceCfg().getBoolean("maintenance");
                reason = BungeeCordMain.getMaintenanceCfg().getString("reason");

                sender.sendMessage(new TextComponent(ChatColor.DARK_GREEN + "Du hast das Netzwerk wieder freigeschaltet!"));
                return;
            }
            return;
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

                try {
                    ConfigurationProvider.getProvider(YamlConfiguration.class).save(BungeeCordMain.getMaintenanceCfg(), BungeeCordMain.getMaintenanceFile());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                maintenance = BungeeCordMain.getMaintenanceCfg().getBoolean("maintenance");
                reason = BungeeCordMain.getMaintenanceCfg().getString("reason");

                sender.sendMessage(new TextComponent(ChatColor.DARK_RED + "Du hast das Netzwerk in den " + ChatColor.DARK_AQUA + "Wartungsmodus " + ChatColor.GOLD + "gesetzt!"));
                return;
            }

            if(maintenance == true) {
                BungeeCordMain.getMaintenanceCfg().set("maintenance", false);
                BungeeCordMain.getMaintenanceCfg().set("reason", "Wartung");

                try {
                    ConfigurationProvider.getProvider(YamlConfiguration.class).save(BungeeCordMain.getMaintenanceCfg(), BungeeCordMain.getMaintenanceFile());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                maintenance = BungeeCordMain.getMaintenanceCfg().getBoolean("maintenance");
                reason = BungeeCordMain.getMaintenanceCfg().getString("reason");

                sender.sendMessage(new TextComponent(ChatColor.DARK_GREEN + "Du hast das Netzwerk wieder freigeschaltet!"));
                return;
            }

            return;
        }
    }
}