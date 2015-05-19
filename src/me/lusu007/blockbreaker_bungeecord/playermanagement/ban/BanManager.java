package me.lusu007.blockbreaker_bungeecord.playermanagement.ban;

import me.lusu007.blockbreaker_bungeecord.mysql.MySQL;
import net.md_5.bungee.BungeeCord;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lukas on 19.05.2015.
 */
public class BanManager {

    public static void ban(String uuid, String playername, String reason, long seconds) {
        long end = 0;
        if(seconds == -1) {
            end = -1;
        } else {
            long current = System.currentTimeMillis();
            long millis = seconds*1000;
            end = current + millis;
        }
        MySQL.update("INSERT INTO ban (name, uuid, end, reason) VALUES ('" + playername + "','" + uuid + "','" + end + "','" + reason + "')");
        if(BungeeCord.getInstance().getPlayer(playername) != null) {
            BungeeCord.getInstance().getPlayer(playername).disconnect("§cDu wurdest vom Server gebannt!\n" +
                    "\n" +
                    "§3Grund: §e" + getReason(uuid) + "\n" +
                    "\n" +
                    "§3Verbleibende Zeit: §e" + getRemainingTime(uuid) + "\n" +
                    "\n" +
                    "§3 Du kannst §c§nkeinen§3 Entbannungsantrag stellen!");
        }
    }

    public static void unban(String uuid) {
        MySQL.update("DELETE FROM ban WHERE uuid='"+uuid+"'");
    }

    public static boolean isBanned(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT end FROM ban WHERE uuid='"+uuid+"'");
        try {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getReason(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM ban WHERE uuid='"+uuid+"'");
        try {
            while(rs.next()) {
                return rs.getString("reason");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Long getEnd(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM ban WHERE uuid='"+uuid+"'");
        try {
            while(rs.next()) {
                return rs.getLong("end");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getBannedPlayers() {
        List<String> list = new ArrayList<String>();
        ResultSet rs = MySQL.getResult("SELECT * FROM ban");
        try {
            while(rs.next()) {
                list.add(rs.getString("player"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static String getRemainingTime(String uuid) {
        long current = System.currentTimeMillis();
        long end = getEnd(uuid);
        if(end == -1) {
            return "§4PERMANENT";
        }
        long millis = end - current;
        long days;
        long hours;
        long minutes;
        long seconds;


        long weeks = (long) Math.floor(millis/1000/60/60/24/7);


        if(weeks < 1) {
            days = (long) Math.floor(millis/1000/60/60/24);
        } else {
            millis = millis - weeks*7*24*60*60*1000;
            days = (long) Math.floor(millis/1000/60/60/24);
        }


        if(days < 1) {
            hours = (long) Math.floor(millis/1000/60/60);
        } else {
            millis = millis - days*24*60*60*1000;
            hours = (long) Math.floor(millis/1000/60/60);
        }


        if(hours < 1) {
            minutes = (long) Math.floor(millis/1000/60);
        } else {
            millis = millis - hours*60*60*1000;
            minutes = (long) Math.floor(millis/1000/60);
        }


        if(minutes < 1) {
            seconds = (long) Math.floor(millis/1000);
        } else {
            millis = millis - minutes*60*1000;
            seconds = (long) Math.floor(millis/1000);
        }

        String Wochen = weeks + " Wochen ";
        String Tage = days + " Tage ";
        String Stunden = hours + " Stunden ";
        String Minuten = minutes + " Minuten ";
        String Sekunden = seconds + " Sekunden ";


        if(weeks == 1) {
            Wochen = " " + weeks + " Woche ";
        }
        if(days == 1) {
            Tage = " " + days + " Tag ";
        }
        if(hours == 1) {
            Stunden = " " + hours + " Stunde ";
        }
        if(minutes == 1) {
            Minuten = " " + minutes + " Minute ";
        }
        if(seconds == 1) {
            Sekunden = " " + seconds + " Sekunde ";
        }



        if(weeks == 0) {
            Wochen = "";
        }
        if(days == 0) {
            Tage = "";
        }
        if(hours == 0) {
            Stunden = "";
        }
        if(minutes == 0) {
            Minuten = "";
        }
        if(seconds == 0) {
            Sekunden = "";
        }


        return "§c"+ Wochen + Tage + Stunden + Minuten + Sekunden;
    }
}
