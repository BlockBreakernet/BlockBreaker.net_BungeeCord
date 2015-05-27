package me.lusu007.blockbreaker_bungeecord.playermanagement.ban.Commands;

import me.lusu007.blockbreaker_bungeecord.playermanagement.ban.BanManager;
import me.lusu007.blockbreaker_bungeecord.playermanagement.ban.BanUnit;
import me.lusu007.blockbreaker_bungeecord.util.PlayerUtil;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.List;

/**
 * Created by Lukas on 27.05.2015.
 */
public class Commands_TempBan extends Command {

    public Commands_TempBan(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("server.ban.tempban")) {
            if (args.length >= 3) {
                ProxiedPlayer pp = BungeeCord.getInstance().getPlayer(args[0]);
                if (BanManager.isBanned(PlayerUtil.getUUID(pp))) {
                    sender.sendMessage("§cDieser Spieler ist bereits gebannt!");
                    return;
                }
                long value;
                try {
                    value = Integer.valueOf(args[1]);
                } catch (NumberFormatException e) {
                    sender.sendMessage("§c<Zahlenwert> muss eine Zahl sein!");
                    return;
                }
                String unitString = args[2];

                String reason = "Admin Temp-Ban";
                if(args.length >= 4) {
                    reason = "";
                    for (int i = 3; i < args.length; i++) {
                        reason += args[i] + " ";
                    }
                }
                List<String> unitList = BanUnit.getUnitsAsString();
                if (unitList.contains(unitString.toLowerCase())) {
                    BanUnit unit = BanUnit.getUnit(unitString);
                    long seconds = value * unit.getToSecond();
                    BanManager.ban(PlayerUtil.getUUID(pp), pp.getName(), reason, seconds + 1);
                    sender.sendMessage("§7Du hast §e" + pp + " §7für §c" + value + " " + unit.getName() + " §7gebannt!");
                    return;
                }
                sender.sendMessage("§cDiese <Einheit> existiert nich!");
                return;
            }
            sender.sendMessage("§c/tempban <Spieler> <Zahlenwert> <Einheit> <Grund>");
            return;
        }
    }
}
