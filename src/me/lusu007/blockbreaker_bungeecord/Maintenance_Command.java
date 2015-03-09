package me.lusu007.blockbreaker_bungeecord;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Created by Lukas on 09.03.2015.
 */
public class Maintenance_Command extends Command implements Listener {

    private BungeeCordMain plugin;

    public Maintenance_Command(String name, BungeeCordMain plugin) {
        super(name);

        this.plugin = plugin;
        ProxyServer.getInstance().getPluginManager().registerListener(plugin, this);

    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer pp = (ProxiedPlayer) sender;

        if (pp.hasPermission("server.set.maintenancemode")) {
            if (args.length >= 1) {

                if (args[0].equalsIgnoreCase("on")) {
                    if(args.length >=2) {
                        String message = "";
                        for (int i = 1; i < args.length; i++) {
                            message = message + args[i] + " ";
                        }

                        BungeeCordMain.maintenancereason = message;

                        pp.sendMessage("§6Du hast die §3Wartungsarbeiten §6aktiviert!");
                        BungeeCordMain.maintenancemode = true;
                        ProxyServer.getInstance().getPlayers().forEach(player -> {
                            if (!BungeeCordMain.maintenanceworker.contains(player.getUUID())) {

                                for (ProxiedPlayer p : BungeeCord.getInstance().getPlayers()) {
                                    p.disconnect(new TextComponent("§cDie Wartungsarbeiten haben begonnen!\n" +
                                            "\n" +
                                            "§3Grund: §e" + BungeeCordMain.maintenancereason + "\n" +
                                            "\n" +
                                            "§3Wenn du meinst, dass das ein Fehler ist melde dich bitte bei einem Admin."));
                                }
                            }
                        });
                    }
                }

                if (args[0].equalsIgnoreCase("off")) {
                    BungeeCordMain.maintenancemode = false;
                    BungeeCordMain.maintenancereason = "";
                }

                if (args[0].equalsIgnoreCase("add")) {
                    if(!BungeeCordMain.maintenanceworker.contains(args[1])) {
                        String target = args[1];
                        BungeeCordMain.maintenanceworker.add(BungeeCordMain.getUUID(target));
                        pp.sendMessage("§6Du hast §3" + target.toString() + " §6erlaubt an den Wartungsarbeiten teilzunehmen.");
                    }
                }

                if (args[0].equalsIgnoreCase("remove")) {
                    String target = args[1];
                    BungeeCordMain.maintenanceworker.remove(BungeeCordMain.getUUID(target));
                    pp.sendMessage("§6Du hast §3" + target.toString() + " §6von den Wartungsarbeiten entfernt!");
                }

            } else {
                pp.sendMessage("§c/maintenance [on/off/add/remove] [Player] [Grund]");
            }
        }
    }

    @EventHandler
    public void onPlayerLogin(PreLoginEvent e) {
        if(BungeeCordMain.maintenancemode == true) {
            if(!BungeeCordMain.maintenanceworker.contains(e.getConnection().getUUID())) {
                e.setCancelReason("§cDer Server wird gewartet !\n" +
                        "\n" +
                        "§3Grund: §e" + BungeeCordMain.maintenancereason + "\n" +
                        "\n" +
                        "§3Wenn du meinst, dass das ein Fehler ist melde dich bitte bei einem Admin.");
                e.getConnection().disconnect();
            }
        }
    }
}

















