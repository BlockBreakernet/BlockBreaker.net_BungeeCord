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
            p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "§cDu §cbist §cin §ckeiner §cparty."));
            return;
        }

        PlayerParty party = PartyManager.getParty(p);

        if(party.isLeader(p)) {
            for(ProxiedPlayer pp : party.getPlayers()) {
                pp.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "§bDie §bParty §bwurde §baufgelöst."));
            }
            p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "§bDu §bhast §bdie §bParty §baufgelöst."));
            PartyManager.deleteParty(party);
            return;
        }else {
            if(party.removePlayer(p)) {
                p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "§bDu §bhast §bdie §bParty §bverlassen."));
                for(ProxiedPlayer pp : party.getPlayers()) {
                    pp.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "§bDer §bSpieler §6" + p.getName() + " §bhat §bdie §bParty §bverlassen."));
                }
                party.getLeader().sendMessage(new TextComponent(BungeeCordMain.partyprefix + "§bDer §bSpieler §6" + p.getName() + " §bhat §bdie §bParty §bverlassen."));
                return;
            }else {
                p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "§bDu §bkannst §bdie §bParty §bnicht §bverlassen."));
                return;
            }
        }
    }

}
