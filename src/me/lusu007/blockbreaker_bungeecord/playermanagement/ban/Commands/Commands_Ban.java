package me.lusu007.blockbreaker_bungeecord.playermanagement.ban.Commands;

import me.lusu007.blockbreaker_bungeecord.playermanagement.ban.BanManager;
import me.lusu007.blockbreaker_bungeecord.util.PlayerUtil;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by Lukas on 27.05.2015.
 */
public class Commands_Ban extends Command {

    public Commands_Ban(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("server.ban.ban")) {
            if (args.length >= 1) {
                ProxiedPlayer pp = BungeeCord.getInstance().getPlayer(args[0]);

                if (BanManager.isBanned(PlayerUtil.getUUID(pp))) {
                    sender.sendMessage("§cDer Spieler wurde bereits gebannt.");
                    return;
                }

                String reason = "Admin Ban";

                if (args.length >= 2) {
                    reason = "";
                    for (int i = 1; i < args.length; i++) {
                        reason += args[i] + " ";
                    }
                }

                BanManager.ban(PlayerUtil.getUUID(pp), pp.getName(), reason, -1);
                sender.sendMessage("§4Du hast §e" + pp + " §4PERMANENT §7gebannt!");
                return;
            }
            sender.sendMessage("§c/ban <Spieler> <Grund>");
            return;
        }
    }
}
