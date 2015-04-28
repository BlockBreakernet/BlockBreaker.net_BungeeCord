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
        e.getPlayer().resetTabHeader();
    }

    public static void setTabList(ProxiedPlayer pp) {

        if(pp.getServer().getInfo().getName().contentEquals("lobby")) {
            pp.setTabHeader(new TextComponent(ChatColor.DARK_RED + "        Block" + ChatColor.DARK_AQUA + "Breaker" + ChatColor.GRAY + ".de        \n" +
                            ChatColor.GOLD + "Lobby"), new TextComponent("Änderbar"));
        }
    }
}
