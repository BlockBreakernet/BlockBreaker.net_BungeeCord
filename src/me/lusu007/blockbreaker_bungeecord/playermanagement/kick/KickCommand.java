package me.lusu007.blockbreaker_bungeecord.playermanagement.kick;

import me.lusu007.blockbreaker_bungeecord.mysql.MySQL;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Lukas on 04.05.2015.
 */
public class KickCommand extends Command {

    public KickCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer pp = (ProxiedPlayer) sender;

        if(pp.hasPermission("player.kick")) {
            if(args.length == 0) {
                pp.sendMessage(new TextComponent("/kick player reason"));
                return;
            }

            if(args.length == 1) {
                sendKickReasons(pp);
                return;
            }

            if(args.length == 2) {
                ProxiedPlayer target = BungeeCord.getInstance().getPlayer(args[0]);

                if(target != null) {
                    if (args[1].equalsIgnoreCase("beleidigung") || args[1].equalsIgnoreCase("insult")) {
                        kickPlayer(target, KickReason.INSULT);
                    } else if (args[1].equalsIgnoreCase("hacks") || args[1].equalsIgnoreCase("onspechacks")) {
                        kickPlayer(target, KickReason.ONSPECHACKS);
                    } else if (args[1].equalsIgnoreCase("provokation") || args[1].equalsIgnoreCase("provocation")) {
                        kickPlayer(target, KickReason.PROVOCATION);
                    } else if (args[1].equalsIgnoreCase("spam")) {
                        kickPlayer(target, KickReason.SPAM);
                    } else {
                        sendKickReasons(pp);
                    }
                }

                return;
            }
        }
    }


    public static void kickPlayer(ProxiedPlayer pp, KickReason reason) {
        String uuid = pp.getUUID();

        if(reason == KickReason.INSULT) {
            pp.disconnect(new TextComponent("§cDu wurdest vom Server gekickt!\n" +
                    "\n" +
                    "§3Grund: §eBeleidigung von Spielern oder Teammitgliedern\n"));
        }

        if(reason == KickReason.ONSPECHACKS) {
            pp.disconnect(new TextComponent("§cDu wurdest vom Server gekickt!\n" +
                    "\n" +
                    "§3Grund: §eVerdacht auf Hacks\n"));
        }

        if(reason == KickReason.PROVOCATION) {
            pp.disconnect(new TextComponent("§cDu wurdest vom Server gekickt!\n" +
                    "\n" +
                    "§3Grund: §eProvokation von Spielern oder Teammitgliedern\n"));
        }

        if(reason == KickReason.SPAM) {
            pp.disconnect(new TextComponent("§cDu wurdest vom Server gekickt!\n" +
                    "\n" +
                    "§3Grund: §e\nSpam"));
        }

        // Kicklogger um 1 erhöhen
        int kickcounterold = 0;

        ResultSet rs = MySQL.getResult("SELECT kickcounter FROM data WHERE uuid = '" + uuid + "'");

        try {
            kickcounterold = rs.getInt("kickcounter");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        MySQL.update("UPDATE data SET kickcounter = '" + kickcounterold + 1 + "'");
    }

    public static void sendKickReasons(ProxiedPlayer pp) {
        pp.sendMessage(new TextComponent(ChatColor.DARK_AQUA + "-=-=-=-=-=-=-=-=-=-=-=-=-=[" + ChatColor.GOLD + "Kick Gründe" + ChatColor.DARK_AQUA + "]=-=-=-=-=-=-=-=-=-=-=-=-=-"));
        pp.sendMessage(new TextComponent(" "));
        pp.sendMessage(new TextComponent(ChatColor.DARK_AQUA + "Bei Beleidigung von Spielern oder Teammitgliedern " + ChatColor.GOLD + "-> " + ChatColor.DARK_AQUA + "/kick [Player] Beleidigung/Insult"));
        pp.sendMessage(new TextComponent(ChatColor.DARK_AQUA + "Bei Verdacht auf Hacks " + ChatColor.GOLD + "-> " + ChatColor.DARK_AQUA + "/kick [Player] Hack"));
        pp.sendMessage(new TextComponent(ChatColor.DARK_AQUA + "Bei Provokation von Spielern oder Teammitgliedern " + ChatColor.GOLD + "-> " + ChatColor.DARK_AQUA + "/kick [Player] Provokation/Provocation"));
        pp.sendMessage(new TextComponent(ChatColor.DARK_AQUA + "Bei Spam im Chat " + ChatColor.GOLD + "-> " + ChatColor.DARK_AQUA + "/kick [Player] Spam"));
        pp.sendMessage(new TextComponent(" "));
        pp.sendMessage(new TextComponent(ChatColor.DARK_AQUA + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"));
    }

    public static void sendKickMessage() {

    }
}
