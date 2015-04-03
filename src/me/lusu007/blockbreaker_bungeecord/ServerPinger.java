package me.lusu007.blockbreaker_bungeecord;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;

/**
 * Created by Lukas on 03.04.2015.
 */
public class ServerPinger extends Command implements Listener {

    public static boolean maintenance = false;

    public ServerPinger(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer pp = (ProxiedPlayer) sender;

        if(pp.hasPermission("server.maintenance")) {
            if(args.length > 1) return;

            if(args.length == 1) {
                if(args[0].equalsIgnoreCase("on")) {
                    maintenance = true;
                }
                if(args[0].equalsIgnoreCase("off")) {
                    maintenance = false;
                }
            } else {
                pp.sendMessage(new TextComponent("/maintenance <on|off>"));
            }
        }
    }
}
