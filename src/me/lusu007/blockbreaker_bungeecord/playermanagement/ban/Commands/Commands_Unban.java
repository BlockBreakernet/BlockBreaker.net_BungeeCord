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
public class Commands_Unban extends Command {

    public Commands_Unban(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("server.ban.unban")) {
            if (args.length == 1) {
                ProxiedPlayer pp = BungeeCord.getInstance().getPlayer(args[0]);
                if (!BanManager.isBanned(PlayerUtil.getUUID(pp))) {
                    sender.sendMessage("§cDieser Spieler ist nicht gebannt!");
                    return;
                }
                BanManager.unban(PlayerUtil.getUUID(pp));
                sender.sendMessage("§7Du hast §e" + pp + " §7entbannt!");
                return;
            }
            sender.sendMessage("§c/unban <Spieler>");
            return;
        }
    }
}
