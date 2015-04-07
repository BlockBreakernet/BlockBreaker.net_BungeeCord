package me.lusu007.blockbreaker_bungeecord.party.listener;

import me.lusu007.blockbreaker_bungeecord.BungeeCordMain;
import me.lusu007.blockbreaker_bungeecord.party.PartyManager;
import me.lusu007.blockbreaker_bungeecord.party.PlayerParty;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Created by Lukas on 07.04.2015.
 */
public class ServerSwitchListener implements Listener {

    @EventHandler
    public void onServerSwitch(ServerSwitchEvent e) {
        ProxiedPlayer player = e.getPlayer();
        if(PartyManager.getParty(player) != null) {
            PlayerParty party = PartyManager.getParty(player);
            if(party.isLeader(player)) {
                for(ProxiedPlayer p : party.getPlayers()) {
                    p.connect(party.getServer());
                    p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "§bThe party joined the server §e"+party.getServer().getName()));
                }
                player.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "§bThe party joined the server §e"+party.getServer().getName()));
            }
        }
    }
}
