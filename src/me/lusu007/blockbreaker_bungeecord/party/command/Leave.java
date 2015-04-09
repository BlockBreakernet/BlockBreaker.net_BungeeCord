package me.lusu007.blockbreaker_bungeecord.party.command;

import me.lusu007.blockbreaker_bungeecord.BungeeCordMain;
import me.lusu007.blockbreaker_bungeecord.party.PartyManager;
import me.lusu007.blockbreaker_bungeecord.party.PlayerParty;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * Created by Lukas on 07.04.2015.
 */
public class Leave extends SubCommand {

    public Leave() {
        super("Leave your party", "", "leave");
    }

    public void onCommand(ProxiedPlayer p, String[] args) {
        if(PartyManager.getParty(p) == null) {
            p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "Du bist in keiner party."));
            return;
        }

        PlayerParty party = PartyManager.getParty(p);

        if(party.isLeader(p)) {
            for(ProxiedPlayer pp : party.getPlayers()) {
                pp.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "Die Party wurde aufgelöst."));
            }
            p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "Du hast die Party aufgelöst."));
            PartyManager.deleteParty(party);
            return;
        }else {
            if(party.removePlayer(p)) {
                p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "Du hast die Party verlassen."));
                for(ProxiedPlayer pp : party.getPlayers()) {
                    pp.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "Der Spieler " + p.getName() + " hat die Party verlassen."));
                }
                party.getLeader().sendMessage(new TextComponent(BungeeCordMain.partyprefix + "Der Spieler " + p.getName() + " hat die Party verlassen."));
                return;
            }else {
                p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "Du kannst die Party nicht verlassen."));
                return;
            }
        }
    }

}
