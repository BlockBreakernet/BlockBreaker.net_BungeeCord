package me.lusu007.blockbreaker_bungeecord.playermanagement.ban.Commands;

import me.lusu007.blockbreaker_bungeecord.BungeeCordMain;
import me.lusu007.blockbreaker_bungeecord.playermanagement.ban.BanManager;
import me.lusu007.blockbreaker_bungeecord.playermanagement.ban.BanUnit;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.List;

/**
 * Created by Lukas on 19.05.2015.
 */
public class BanCommands extends Command {

    public BanCommands(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("server.ban.ban")) {
            if (cmd.getName().equalsIgnoreCase("ban")) {
                if (args.length >= 1) {
                    ProxiedPlayer pp = BungeeCord.getInstance().getPlayer(args[0]);
                    if (BanManager.isBanned(getUUID(pp))) {
                        sender.sendMessage("§cDieser Spieler ist bereits gebannt!");
                        return;
                    }
                    String reason = "Admin Ban";
                    if(args.length >= 2) {
                        reason = "";
                        for (int i = 1; i < args.length; i++) {
                            reason += args[i] + " ";
                        }
                    }
                    BanManager.ban(getUUID(pp), pp.getName(), reason, -1);
                    sender.sendMessage("§4Du hast §e" + pp + " §4PERMANENT §7gebannt!");
                    return;
                }
                sender.sendMessage("§c/ban <Spieler> <Grund>");
                return;

            }
        }


        if (sender.hasPermission("server.ban.tempban")) {
            if (cmd.getName().equalsIgnoreCase("tempban")) {
                if (args.length >= 3) {
                    ProxiedPlayer pp = BungeeCord.getInstance().getPlayer(args[0]);
                    if (BanManager.isBanned(getUUID(pp))) {
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
                        BanManager.ban(getUUID(pp), pp.getName(), reason, seconds + 1);
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


        if (sender.hasPermission("server.ban.check")) {
            if (cmd.getName().equalsIgnoreCase("check")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("list")) {
                        List<String> list = BanManager.getBannedPlayers();
                        if (list.size() == 0) {
                            sender.sendMessage("§cEs sind aktuell keine Spieler gebannt!");
                            return;
                        }
                        sender.sendMessage("§7----------§6§1Ban-Liste §7----------");
                        for (String allBanned : BanManager.getBannedPlayers()) {
                            sender.sendMessage("§e" + allBanned + " §7(Grund: §r" + BanManager.getReason(getUUID(allBanned)) + "§7)");
                        }
                        return;
                    }
                    ProxiedPlayer pp = BungeeCord.getInstance().getPlayer(args[0]);
                    sender.sendMessage("§7----------§6§1Ban-Infos §7----------");
                    sender.sendMessage("§eName: §r" + pp);
                    sender.sendMessage("§eGebannt: §r" + (BanManager.isBanned(getUUID(pp)) ? "§cJa!" : "§aNein!"));
                    if (BanManager.isBanned(getUUID(pp))) {
                        sender.sendMessage("§eGrund: §r" + BanManager.getReason(getUUID(pp)));
                        sender.sendMessage("§eVerbleibende Zeit: §r" + BanManager.getRemainingTime(getUUID(pp)));
                    }
                    return;
                }
                sender.sendMessage("§c/check (list) <Spieler>");
                return;
            }
        }


        if (sender.hasPermission("server.ban.unban")) {
            if (cmd.getName().equalsIgnoreCase("unban")) {
                if (args.length == 1) {
                    ProxiedPlayer pp = BungeeCord.getInstance().getPlayer(args[0]);
                    if (!BanManager.isBanned(getUUID(pp))) {
                        sender.sendMessage("§cDieser Spieler ist nicht gebannt!");
                        return;
                    }
                    BanManager.unban(getUUID(pp));
                    sender.sendMessage("§7Du hast §e" + pp + " §7entbannt!");
                    return;
                }
                sender.sendMessage("§c/unban <Spieler>");
                return;
            }
        }
        return;
    }


    @SuppressWarnings("deprecation")
    public static String getUUID(ProxiedPlayer playername) {
        return playername.getUUID();
    }


}
