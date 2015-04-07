package me.lusu007.blockbreaker_bungeecord.party.command;

import me.lusu007.blockbreaker_bungeecord.BungeeCordMain;
import me.lusu007.blockbreaker_bungeecord.party.PartyManager;
import me.lusu007.blockbreaker_bungeecord.party.PlayerParty;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * Created by Lukas on 07.04.2015.
 */
public class List extends SubCommand {

    public List() {
        super("Listet alle Mitglieder der Party auf.", "", "list");
    }

    public void onCommand(ProxiedPlayer p, String[] args) {
        if(PartyManager.getParty(p) == null) {
            p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "§cDu §cbist §cin §ckeiner §cparty."));
            return;
        }

        PlayerParty party = PartyManager.getParty(p);

        String leader = "§3Leiter§7: §5"+party.getLeader().getName();
        String players = "§8Mitlgieder§7: §b";

        if(!party.getPlayers().isEmpty()) {
            for(ProxiedPlayer pp : party.getPlayers()) {
                players += pp.getName() + "§7, §b";
            }
            players = players.substring(0, players.lastIndexOf("§7, §b"));
        }else {
            players += "Empty";
        }


        p.sendMessage(new TextComponent("§8§m----------[§5§lPARTY§8]§m----------"));

        p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + leader));
        p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + players));

        p.sendMessage(new TextComponent("§8§m-----------------------------------"));
        return;
    }
}
