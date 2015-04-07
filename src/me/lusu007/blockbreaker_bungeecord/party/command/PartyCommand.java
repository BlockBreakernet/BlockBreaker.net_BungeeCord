package me.lusu007.blockbreaker_bungeecord.party.command;

import me.lusu007.blockbreaker_bungeecord.BungeeCordMain;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * Created by Lukas on 07.04.2015.
 */
public class PartyCommand extends Command {

    private List<SubCommand> cmds = new ArrayList<SubCommand>();

    public PartyCommand() {
        super("party");
        cmds.add(new Invite());
        cmds.add(new Join());
        cmds.add(new Leave());
        cmds.add(new Kick());
        cmds.add(new me.lusu007.blockbreaker_bungeecord.party.command.List());
        cmds.add(new Create());
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent("[PARTY] You have to be a player."));
            return;
        }

        ProxiedPlayer p = (ProxiedPlayer)sender;

        if(args.length == 0) {
            p.sendMessage(new TextComponent("§8§m----------[§5§lPARTY§8]§m----------"));
            for(SubCommand sc : cmds) {
                p.sendMessage(new TextComponent("§8/§5party "+aliases(sc)+ " " + sc.getUsage() + " §8- §7"+sc.getMessage()));
            }
            p.sendMessage(new TextComponent("§8§m-----------------------------------"));
            return;
        }

        SubCommand sc = getCommand(args[0]);

        if(sc == null) {
            p.sendMessage(new TextComponent(BungeeCordMain.partyprefix + "§cThat §ccommand §cdoesn't §cexist."));
            return;
        }

        Vector<String> a = new Vector<String>(Arrays.asList(args));
        a.remove(0);
        args = a.toArray(new String[a.size()]);

        sc.onCommand(p, args);
        return;
    }

    private String aliases(SubCommand sc) {
        String fin = "";

        for(String a : sc.getAliases()) {
            fin += a + " | ";
        }

        return fin.substring(0, fin.lastIndexOf(" | "));
    }

    private SubCommand getCommand(String name) {
        for(SubCommand sc : cmds) {
            if(sc.getClass().getSimpleName().equalsIgnoreCase(name)) return sc;
            for(String alias : sc.getAliases()) if(alias.equalsIgnoreCase(name)) return sc;
        }
        return null;
    }
}
