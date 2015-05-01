package me.lusu007.blockbreaker_bungeecord.features;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Created by Lukas on 28.04.2015.
 */
public class TabList implements Listener {

    @EventHandler
    public void onServerSwitch(ServerSwitchEvent e) {
        setTabList(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerDisconnectEvent e) {
        e.getPlayer().setTabHeader(new TextComponent(" "), new TextComponent(" "));
    }

    public static void setTabList(ProxiedPlayer pp) {

        if(pp.getServer().getInfo().getName().contentEquals("lobby")) {
            pp.setTabHeader(new TextComponent(ChatColor.AQUA + "        BlockBreaker" + ChatColor.GREEN + ".de        \n" +
                            ChatColor.GOLD + "Lobby"), new TextComponent("Änderbar"));
        }

        if(pp.getServer().getInfo().getName().contentEquals("sw")) {
            pp.setTabHeader(new TextComponent(ChatColor.AQUA + "        BlockBreaker" + ChatColor.GREEN + ".de        \n" +
                    ChatColor.AQUA + "SkyWars"), new TextComponent("Änderbar"));
        }
    }
}
