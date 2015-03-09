package me.lusu007.blockbreaker_bungeecord;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.event.EventHandler;

/**
 * Created by Lukas on 09.03.2015.
 */
public class Maintenance_Command extends Command {

    public Maintenance_Command(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer pp = (ProxiedPlayer) sender;

        if (pp.hasPermission("server.set.maintenancemode")) {
            if (args.length >= 1) {

                if (args[0].equalsIgnoreCase("on")) {
                    pp.sendMessage("§6Du hast die §3Wartungsarbeiten aktiviert!");
                    BungeeCordMain.maintenancemode = true;
                    ProxyServer.getInstance().getPlayers().forEach(player -> {
                        if(!BungeeCordMain.maintenanceworker.contains(pp.getName())) {
                            pp.disconnect("§cDie Wartungsarbeiten haben begonnen!\n" +
                                    "\n" +
                                    "§3Grund: §e" + BungeeCordMain.maintenancereason + "\n" +
                                    "\n" +
                                    "§3Wenn du meinst, dass das ein Fehler ist melde dich bitte bei einem Admin.");
                        }
                    });
                }

                if (args[0].equalsIgnoreCase("off")) {
                    BungeeCordMain.maintenancemode = false;
                }

                if (args[0].equalsIgnoreCase("add")) {
                    if(!BungeeCordMain.maintenanceworker.contains(args[1])) {
                        String target = args[1];
                        BungeeCordMain.maintenanceworker.add(target);
                    }
                }

                if (args[0].equalsIgnoreCase("remove")) {
                    String target = args[1];
                    BungeeCordMain.maintenanceworker.remove(target);
                }

            } else {
                pp.sendMessage("§c/maintenance [on/off/add/remove] [Player]");
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PostLoginEvent e) {
        ProxiedPlayer pp = e.getPlayer();

        if (BungeeCordMain.maintenancemode == true) {
            if (!BungeeCordMain.maintenanceworker.contains(pp.getName())) {
                pp.disconnect("§cDer Server wird gewartet!\n" +
                        "\n" +
                        "§3Grund: §e" + BungeeCordMain.maintenancereason + "\n" +
                        "\n" +
                        "§3Wenn du meinst, dass das ein Fehler ist melde dich bitte bei einem Admin.");
            }
        }
    }
}
