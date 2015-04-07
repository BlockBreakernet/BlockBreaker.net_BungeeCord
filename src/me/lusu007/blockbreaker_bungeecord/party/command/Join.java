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
public class Join extends SubCommand {

    public Join() {
        super("Trete einer Party bei.", "<Name>", "join");
    }

    public void onCommand(ProxiedPlayer p, String[] args) {
        if(args.length == 0) {
            p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "§cBitte gebe den Spielernamen an."));
            return;
        }

        if(PartyManager.getParty(p) != null) {
            p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "§cDu bist schon in einer Party. §cBenutze /leave um die Party zu verlassen."));
            return;
        }

        ProxiedPlayer pl = BungeeCord.getInstance().getPlayer(args[0]);

        if(pl == null) {
            p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "§cDieser Spieler ist nicht online"));
            return;
        }

        if(PartyManager.getParty(pl) == null) {
            p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "§cDieser Spieler hat keine Party erstellt."));
            return;
        }

        PlayerParty party = PartyManager.getParty(pl);

        if(party.addPlayer(p)) {
            for(ProxiedPlayer pp : party.getPlayers()) {
                pp.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "§bDer Spieler §6" + p.getName() + " §bist der Party beigetreten."));
            }
            party.getLeader().sendMessage(new TextComponent(BungeeCordMain.partyprefix + "§bDer Spieler §6" + p.getName() + " §bist der Party beigetreten."));
        }else {
            p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "§cDu kannst der Party nicht beitreten."));
            return;
        }
    }

}
