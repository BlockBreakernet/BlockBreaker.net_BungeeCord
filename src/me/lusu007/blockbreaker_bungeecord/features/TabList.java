package me.lusu007.blockbreaker_bungeecord.features;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Created by Lukas on 28.04.2015.
 */
public class TabList implements Listener {

    @EventHandler
    public void onQuit(PlayerDisconnectEvent e) {
        e.getPlayer().setTabHeader(new TextComponent(" "), new TextComponent(" "));
    }

    @EventHandler
    public void onSwitch(ServerConnectedEvent e) {
        if(e.getServer().getInfo().getName().contentEquals("lobby")) {
            e.getPlayer().setTabHeader(new TextComponent(ChatColor.AQUA + "        BlockBreaker" + ChatColor.GREEN + ".de        \n" +
                    ChatColor.GOLD + "Lobby"), new TextComponent("Änderbar"));
        } else if(e.getServer().getInfo().getName().contentEquals("sw")) {
            e.getPlayer().setTabHeader(new TextComponent(ChatColor.AQUA + "        BlockBreaker" + ChatColor.GREEN + ".de        \n" +
                    ChatColor.AQUA + "SkyWars"), new TextComponent("Änderbar"));
        } else if(e.getServer().getInfo().getName().contentEquals("sg")) {
            e.getPlayer().setTabHeader(new TextComponent(ChatColor.AQUA + "        BlockBreaker" + ChatColor.GREEN + ".de        \n" +
                    ChatColor.AQUA + "SurvivalGames"), new TextComponent("Änderbar"));
        } else if(e.getServer().getInfo().getName().contentEquals("rpg")) {
            e.getPlayer().setTabHeader(new TextComponent(ChatColor.AQUA + "        BlockBreaker" + ChatColor.GREEN + ".de        \n" +
                    ChatColor.AQUA + "RPG"), new TextComponent("Änderbar"));
        } else if(e.getServer().getInfo().getName().contentEquals("stc")) {
            e.getPlayer().setTabHeader(new TextComponent(ChatColor.AQUA + "        BlockBreaker" + ChatColor.GREEN + ".de        \n" +
                    ChatColor.AQUA + "SafeTheCake"), new TextComponent("Änderbar"));
        }
    }
}
