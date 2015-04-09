package me.lusu007.blockbreaker_bungeecord.party.command;

import me.lusu007.blockbreaker_bungeecord.BungeeCordMain;
import me.lusu007.blockbreaker_bungeecord.party.PartyManager;
import me.lusu007.blockbreaker_bungeecord.party.PlayerParty;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * Created by Lukas on 07.04.2015.
 */
public class Kick extends SubCommand {

    public Kick() {
        super("Kicke einen Spieler aus der Party.", "<Name>", "kick");
    }

    public void onCommand(ProxiedPlayer p, String[] args) {
        if(args.length == 0) {
            p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "Bitte gebe den Spielernamen an."));
            return;
        }

        if(PartyManager.getParty(p) == null) {
            p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "Du bist in keiner Party."));
            return;
        }

        PlayerParty party = PartyManager.getParty(p);

        if(!party.isLeader(p)) {
            p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "Du bist nicht der Party Leiter."));
            return;
        }

        ProxiedPlayer pl = BungeeCord.getInstance().getPlayer(args[0]);

        if(pl == null) {
            p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "Dieser Spiel ist nicht online."));
            return;
        }

        if(party.removePlayer(pl)) {
            p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "Du hast " + pl.getName() + " aus der Party gekickt."));
            for(ProxiedPlayer pp : party.getPlayers()) {
                pp.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "Der Spieler §6" + pl.getName() + " wurde aus der Party gekickt."));
            }
            pl.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "Du wurdest aus der Party gekickt."));
            return;
        }else {
            p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "Du kannst diesen Spieler nicht kicken."));
            return;
        }
    }

}
