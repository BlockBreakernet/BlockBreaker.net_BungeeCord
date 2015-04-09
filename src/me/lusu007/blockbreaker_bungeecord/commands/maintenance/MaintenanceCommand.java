package me.lusu007.blockbreaker_bungeecord.commands.maintenance;

import me.lusu007.blockbreaker_bungeecord.BungeeCordMain;
import me.lusu007.blockbreaker_bungeecord.mysql.MySQLMethods;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;

/**
 * Created by Lukas on 03.04.2015.
 */
public class MaintenanceCommand extends Command implements Listener {

    public MaintenanceCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer pp = (ProxiedPlayer) sender;

        if(pp.hasPermission("server.maintenance")) {
            if(args.length < 1) return;

            if(args.length == 1) {
                if(args[0].equalsIgnoreCase("on")) {
                    MySQLMethods.setMaintenance(true);
                    BungeeCordMain.maintenance = true;
                }
                if(args[0].equalsIgnoreCase("off")) {
                    MySQLMethods.setMaintenance(false);
                    BungeeCordMain.maintenance = false;
                }
            }

            if(args.length == 2 || args.length == 3) {
                if(args[0].equalsIgnoreCase("add")) {

                }

                if(args[0].equalsIgnoreCase("remove")) {

                }

                if(args[0].equalsIgnoreCase("list")) {

                }
            }
        }
    }
}
