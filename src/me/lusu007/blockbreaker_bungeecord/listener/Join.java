package me.lusu007.blockbreaker_bungeecord.listener;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Created by Lukas on 06.04.2015.
 */
public class Join implements Listener {

    @EventHandler
    public void onLogin(ServerSwitchEvent e) {
        ProxiedPlayer pp = e.getPlayer();

        pp.resetTabHeader();
        pp.setTabHeader(new TextComponent("BlockBreaker.de Servernetzwerk"), new TextComponent(pp.getServer().getInfo().getName() + " - " + pp.getServer().getInfo().getPlayers().size() + "/100"));
    }
}
