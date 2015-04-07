package me.lusu007.blockbreaker_bungeecord.party.command;

import me.lusu007.blockbreaker_bungeecord.BungeeCordMain;
import me.lusu007.blockbreaker_bungeecord.party.PartyManager;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * Created by Lukas on 07.04.2015.
 */
public class Create extends SubCommand {

    public Create() {
        super("Create a party", "", "create");
    }

    public void onCommand(ProxiedPlayer p, String[] args) {
        if(PartyManager.getParty(p) != null) {
            p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "§cDu bist schon in einer Party. §cBenutze /leave um die Party zu verlassen."));
            return;
        }

        PartyManager.createParty(p);

        p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "§bDu hast eine Party erstellt."));
        return;
    }

}
