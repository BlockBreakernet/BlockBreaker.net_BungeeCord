package me.lusu007.blockbreaker_bungeecord.party;

import me.lusu007.blockbreaker_bungeecord.BungeeCordMain;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lukas on 07.04.2015.
 */
public class PlayerParty {

    private ProxiedPlayer leader;

    private List<ProxiedPlayer> players;

    private List<ProxiedPlayer> invitations;

    public PlayerParty(ProxiedPlayer leader) {
        this.leader = leader;
        this.players = new ArrayList<ProxiedPlayer>();
        this.invitations = new ArrayList<ProxiedPlayer>();
    }

    public boolean isLeader(ProxiedPlayer player) {
        return this.leader == player;
    }

    public List<ProxiedPlayer> getPlayers() {
        return players;
    }

    public ProxiedPlayer getLeader() {
        return leader;
    }

    public boolean isInParty(ProxiedPlayer player) {
        if(isLeader(player)) {
            return true;
        }else if(players.contains(player)) {
            return true;
        }else {
            return false;
        }
    }

    public boolean addPlayer(ProxiedPlayer player) {
        if(!players.contains(player) && invitations.contains(player)) {
            players.add(player);
            invitations.remove(player);
            return true;
        }else {
            return false;
        }
    }

    public boolean removePlayer(ProxiedPlayer player) {
        if(players.contains(player)) {
            players.remove(player);
            return true;
        }else {
            return false;
        }
    }

    public ServerInfo getServer() {
        return leader.getServer().getInfo();
    }

    public void invite(final ProxiedPlayer p) {
        invitations.add(p);
        p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "Du wurdest von " + leader.getName() + " in eine Party eingeladen."));
        BungeeCord.getInstance().getScheduler().schedule(BungeeCordMain.getInstance(), new Runnable() {
            public void run() {
                if(invitations.contains(p)) {
                    invitations.remove(p);
                    p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "§bYour invitations has been expired."));
                    leader.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "§bThe invitation to §6" + p.getName() + " §bhas been expired."));
                }
            }
        }, 30L, TimeUnit.SECONDS);
    }
}
