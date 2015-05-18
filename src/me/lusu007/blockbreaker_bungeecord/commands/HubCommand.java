package me.lusu007.blockbreaker_bungeecord.commands;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by Lukas on 17.05.2015.
 */
public class HubCommand extends Command {

    public HubCommand(String name) {
        super(name, "server.command.hub", "l", "leave", "lobby");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer) {
            ProxiedPlayer pp = (ProxiedPlayer) sender;

            pp.connect(BungeeCord.getInstance().getServerInfo("lobby"));
            return;
        }
    }
}
