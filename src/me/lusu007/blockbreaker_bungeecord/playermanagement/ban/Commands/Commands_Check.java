package me.lusu007.blockbreaker_bungeecord.playermanagement.ban.Commands;

import me.lusu007.blockbreaker_bungeecord.playermanagement.ban.BanManager;
import me.lusu007.blockbreaker_bungeecord.util.PlayerUtil;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.List;

/**
 * Created by Lukas on 27.05.2015.
 */
public class Commands_Check extends Command {

    public Commands_Check(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("server.ban.check")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("list")) {
                    List<String> list = BanManager.getBannedPlayers();
                    if (list.size() == 0) {
                        sender.sendMessage("§cEs sind aktuell keine Spieler gebannt!");
                        return;
                    }
                    sender.sendMessage("§7----------§6§1Ban-Liste §7----------");
                    for (String allBanned : BanManager.getBannedPlayers()) {
                        sender.sendMessage("§e" + allBanned + " §7(Grund: §r" + BanManager.getReason(PlayerUtil.getUUID(BungeeCord.getInstance().getPlayer(allBanned))) + "§7)");
                    }
                    return;
                }
                ProxiedPlayer pp = BungeeCord.getInstance().getPlayer(args[0]);
                sender.sendMessage("§7----------§6§1Ban-Infos §7----------");
                sender.sendMessage("§eName: §r" + pp);
                sender.sendMessage("§eGebannt: §r" + (BanManager.isBanned(PlayerUtil.getUUID(pp)) ? "§cJa!" : "§aNein!"));
                if (BanManager.isBanned(PlayerUtil.getUUID(pp))) {
                    sender.sendMessage("§eGrund: §r" + BanManager.getReason(PlayerUtil.getUUID(pp)));
                    sender.sendMessage("§eVerbleibende Zeit: §r" + BanManager.getRemainingTime(PlayerUtil.getUUID(pp)));
                }
                return;
            }
            sender.sendMessage("§c/check (list) <Spieler>");
            return;
        }
    }
}
