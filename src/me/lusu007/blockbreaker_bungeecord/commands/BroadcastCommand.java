package me.lusu007.blockbreaker_bungeecord.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by Lukas on 09.03.2015.
 */
public class BroadcastCommand extends Command {

    public BroadcastCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer pp = (ProxiedPlayer) sender;

        if(pp.hasPermission("server.broadcast")) {
            if (args.length > 0) {
                String message = "";
                for (int i = 0; i < args.length; i++) {
                    message = message + args[i] + " ";
                }

                message = ChatColor.translateAlternateColorCodes('&', message);

                ProxyServer.getInstance().broadcast(message);

            } else {
                pp.sendMessage("§c/broadcast [Nachricht]");
            }
        }
    }
}
