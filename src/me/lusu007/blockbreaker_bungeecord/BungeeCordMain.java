package me.lusu007.blockbreaker_bungeecord;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.ArrayList;

/**
 * - server.set.maintenancemode
 * - server.broadcast
 */

/**
 * Created by Lukas on 09.03.2015.
 */
public class BungeeCordMain extends Plugin {

    public static boolean maintenancemode;
    public static String maintenancereason;
    public static ArrayList<String> maintenanceworker = new ArrayList<>();

    @Override
    public void onEnable() {

        registerCommands();

        System.out.println("[BlockBreaker-Bungee] BlockBreaker-Bungee enabled!");
    }

    @Override
    public void onDisable() {
        System.out.println("[BlockBreaker-Bungee] BlockBreaker-Bungee disabled!");
    }

    private void registerCommands() {
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Broadcast_Command("broadcast"));
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Maintenance_Command("maintenance"));
    }

}
