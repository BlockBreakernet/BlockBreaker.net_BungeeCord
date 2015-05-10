package me.lusu007.blockbreaker_bungeecord;

import me.lusu007.blockbreaker_bungeecord.commands.BroadcastCommand;
import me.lusu007.blockbreaker_bungeecord.features.*;
import me.lusu007.blockbreaker_bungeecord.mysql.MySQL;
import me.lusu007.blockbreaker_bungeecord.mysql.MySQLMethods;
import me.lusu007.blockbreaker_bungeecord.playermanagement.kick.KickCommand;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * - server.broadcast
 */

/**
 * Created by Lukas on 09.03.2015.
 */
public class BungeeCordMain extends Plugin {

    public static String standardmotd = ChatColor.YELLOW + "BlockBreaker.net " + ChatColor.GRAY + "|" + ChatColor.DARK_AQUA + " BlockBreaker Network                             " + ChatColor.YELLOW + "[" + ChatColor.RED + "1.8"+ ChatColor.YELLOW +"]" +
            "\n" + ChatColor.DARK_RED;
    public static String submotd = ChatColor.YELLOW + "+" + ChatColor.DARK_PURPLE + "Server Release " + ChatColor.GRAY + "|" + ChatColor.YELLOW + " +" + ChatColor.AQUA + "RPG Release";

    public static int maintenanceend = 0;

    public static boolean maintenance = false;

    private static BungeeCordMain instance;

    //Maintenace File
    public static File file = new File(getInstance().getDataFolder().getPath(), "Wartung.yml");
    public static Configuration config;

    @Override
    public void onEnable() {

        registerAll();


        instance = this;

        createMySQLCFG();
        createMaintenaceCFG();

        MySQL.connect();
        MySQLMethods.createTableIfNotExists();
        MySQLMethods.createData();

        MySQLMethods.setMOTD(standardmotd);
        MySQLMethods.setSubMOTD(submotd);

        System.out.println("[BlockBreaker-Bungee] BlockBreaker-Bungee enabled!");
    }

    @Override
    public void onDisable() {
        System.out.println("[BlockBreaker-Bungee] BlockBreaker-Bungee disabled!");
    }

    private void registerAll() {
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new BroadcastCommand("broadcast"));
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new KickCommand("kick"));
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Maintenance("wartung"));
        BungeeCord.getInstance().getPluginManager().registerListener(this, new TabList());
        BungeeCord.getInstance().getPluginManager().registerListener(this, new AutoReconnect());
        BungeeCord.getInstance().getPluginManager().registerListener(this, new ServerSwitch());
        BungeeCord.getInstance().getPluginManager().registerListener(this, new MySQLData());
    }

    public static BungeeCordMain getInstance() {
        return instance;
    }

    public static void createMySQLCFG() {
        try {
            if (!getInstance().getDataFolder().exists()) {
                getInstance().getDataFolder().mkdir();
            }

            File file = new File(getInstance().getDataFolder().getPath(), "MySQL.yml");
            if (!file.exists()) {
                file.createNewFile();
            }
            Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);

            if(config.get("host") == null) {
                config.set("host", "host");
                config.set("port", "3306");
                config.set("database", "database");
                config.set("user", "username");
                config.set("password", "password");

                ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, file);
            }

            MySQL.host = config.getString("host");
            MySQL.port = config.getString("port");
            MySQL.database = config.getString("database");
            MySQL.username = config.getString("user");
            MySQL.password = config.getString("password");
        } catch(IOException e) {
        }
    }

    public static void createMaintenaceCFG() {
        try {
            if (!getInstance().getDataFolder().exists()) {
                getInstance().getDataFolder().mkdir();
            }


            if (!file.exists()) {
                file.createNewFile();
            }

            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);

            if(config.get("maintenance") == null) {
                config.set("maintenance", "false");
                config.set("reason", "Wartung");

                ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, file);
            }

            Maintenance.maintenance = config.getBoolean("maintenance");
            Maintenance.reason = config.getString("reason");
        } catch(IOException e) {
        }
    }

    public static Configuration getMaintenanceCfg() {
        return config;
    }
}
