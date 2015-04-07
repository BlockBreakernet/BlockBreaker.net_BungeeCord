package me.lusu007.blockbreaker_bungeecord.party;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lukas on 07.04.2015.
 */
public class PartyManager {

    private static List<PlayerParty> parties = new ArrayList<PlayerParty>();

    public static PlayerParty getParty(ProxiedPlayer player) {
        for(PlayerParty party : parties) {
            if(party.isInParty(player)) {
                return party;
            }
        }
        return null;
    }

    public static boolean createParty(ProxiedPlayer player) {
        if(getParty(player) == null) {
            parties.add(new PlayerParty(player));
            return true;
        }else {
            return false;
        }
    }

    public static boolean deleteParty(ProxiedPlayer player) {
        if(getParty(player) != null) {
            if(getParty(player).isLeader(player)) {
                for(ProxiedPlayer p : getParty(player).getPlayers()) {
                    getParty(player).removePlayer(p);
                }
                parties.remove(getParty(player));
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    public static List<PlayerParty> getParties() {
        return parties;
    }

    public static void deleteParty(PlayerParty party) {
        if(party != null) {
            for(int i = 0; i < party.getPlayers().size(); i++) {
                if(party.getPlayers().get(i) != null) {
                    party.getPlayers().remove(i);
                }
            }
            parties.remove(party);
        }
    }
}
