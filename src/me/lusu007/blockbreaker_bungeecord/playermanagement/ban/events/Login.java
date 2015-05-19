package me.lusu007.blockbreaker_bungeecord.playermanagement.ban.events;

import me.lusu007.blockbreaker_bungeecord.playermanagement.ban.BanManager;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Created by Lukas on 19.05.2015.
 */
public class Login implements Listener {

    @EventHandler
    public void onPlayerLogin(LoginEvent e) {
        ProxiedPlayer p = BungeeCord.getInstance().getPlayer(e.getConnection().getName());
        if(BanManager.isBanned(p.getUniqueId().toString())) {
            long current = System.currentTimeMillis();
            long end = BanManager.getEnd(p.getUniqueId().toString());
            if(current < end | end == -1) {
                e.setCancelled(true);
                e.setCancelReason("§cDu wurdest vom Server gebannt!\n" +
                        "\n" +
                        "§3Grund: §e" + BanManager.getReason(p.getUniqueId().toString()) + "\n" +
                        "\n" +
                        "§3Verbleibende Zeit: §e" + BanManager.getRemainingTime(p.getUniqueId().toString()) + "\n" +
                        "\n" +
                        "§3 Du kannst §c§nkeinen§3 Entbannungsantrag stellen!");
            }
        }
    }
}
