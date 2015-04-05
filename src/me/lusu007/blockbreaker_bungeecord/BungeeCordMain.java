package me.lusu007.blockbreaker_bungeecord;

import me.lusu007.blockbreaker_bungeecord.mysql.MySQL;
import me.lusu007.blockbreaker_bungeecord.mysql.MySQLMethods;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Listener;
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
public class BungeeCordMain extends Plugin implements Listener {

    public static String standardmotd = ChatColor.YELLOW + "BlockBreaker.net " + ChatColor.GRAY + "|" + ChatColor.DARK_AQUA + " BlockBreaker Network                 " + ChatColor.YELLOW + "[" + ChatColor.RED + "1.8"+ ChatColor.YELLOW +"]" +
            "\n" + ChatColor.DARK_RED;
    public static String submotd = ChatColor.YELLOW + "+" + ChatColor.DARK_PURPLE + "Server Release " + ChatColor.GRAY + "|" + ChatColor.YELLOW + " +" + ChatColor.AQUA + "RPG Release";

    @Override
    public void onEnable() {

        registerCommands();
        registerEvents();


        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdir();
            }

            File file = new File(getDataFolder().getPath(), "MySQL.yml");
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

        MySQL.connect();
        MySQLMethods.createTableIfNotExists();
        MySQLMethods.createData();

        MySQLMethods.setSubMOTD(submotd);

        System.out.println("[BlockBreaker-Bungee] BlockBreaker-Bungee enabled!");
    }

    private void registerEvents() {
        new MOTD_Setter(this);
    }

    @Override
    public void onDisable() {
        System.out.println("[BlockBreaker-Bungee] BlockBreaker-Bungee disabled!");
    }

    private void registerCommands() {
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Broadcast_Command("broadcast"));
    }
}
