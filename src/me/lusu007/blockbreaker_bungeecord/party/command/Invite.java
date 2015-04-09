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
public class Invite extends SubCommand {

    public Invite() {
        super("Invite a player in your party", "<Name>", "invite");
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
            p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "Dieser SPieler ist nicht online."));
            return;
        }

        party.invite(pl);

        p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "Du hast " + pl.getName() + " in deine Party eingeladen."));
    }

}
