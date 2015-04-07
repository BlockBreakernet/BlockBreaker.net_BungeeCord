package me.lusu007.blockbreaker_bungeecord.party.listener;

import me.lusu007.blockbreaker_bungeecord.BungeeCordMain;
import me.lusu007.blockbreaker_bungeecord.party.PartyManager;
import me.lusu007.blockbreaker_bungeecord.party.PlayerParty;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Created by Lukas on 07.04.2015.
 */
public class PlayerDisconnectListener implements Listener {

    @EventHandler
    public void onPlayerDisconnect(PlayerDisconnectEvent e) {
        ProxiedPlayer player = e.getPlayer();

        if(PartyManager.getParty(player) != null) {
            PlayerParty party = PartyManager.getParty(player);
            if(party.isLeader(player)) {
                for(ProxiedPlayer p : party.getPlayers()) {
                    p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "§bThe §bLeader §bhas §bleft §bthe §bparty. §bSo §bthe §bparty §bwas §bdissolved."));
                }
                PartyManager.deleteParty(party);
            }else {
                party.removePlayer(player);
                for(ProxiedPlayer p : party.getPlayers()) {
                    p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "§bThe §bplayer §6"+player.getName()+" §bhas §bleft §bthe §bparty."));
                }
                party.getLeader().sendMessage(new TextComponent(BungeeCordMain.partyprefix + "§bThe §bplayer §6"+player.getName()+" §bhas §bleft §bthe §bparty."));
            }
        }
    }
}
