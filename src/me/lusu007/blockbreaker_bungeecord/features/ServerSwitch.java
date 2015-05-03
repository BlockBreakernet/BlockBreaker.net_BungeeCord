package me.lusu007.blockbreaker_bungeecord.features;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lukas on 03.05.2015.
 */
public class ServerSwitch implements Listener {

    @EventHandler
    public void onSwitch(ServerSwitchEvent e) {
        ProxiedPlayer pp = e.getPlayer();

        if(!pp.getServer().getInfo().getName().equals("lobby")){
            SimpleDateFormat sdfmt = new SimpleDateFormat();
            sdfmt.applyPattern("H:mm a, MMMM M, yyyy");

            TextComponent headermessage = new TextComponent(" ");
            TextComponent message = new TextComponent(ChatColor.DARK_AQUA + "Du bist auf Server " + ChatColor.YELLOW + pp.getServer().getInfo().getName().toUpperCase() + ChatColor.DARK_AQUA + ", die aktuelle Zeit ist " + ChatColor.YELLOW + sdfmt.format(new Date()) + ChatColor.DARK_AQUA + ".");
            TextComponent footermessage = new TextComponent(" ");

            pp.sendMessage(headermessage);
            pp.sendMessage(message);
            pp.sendMessage(footermessage);
        }
    }
}
