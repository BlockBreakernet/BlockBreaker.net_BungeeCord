package me.lusu007.blockbreaker_bungeecord.mysql;

import me.lusu007.blockbreaker_bungeecord.BungeeCordMain;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Locale;

/**
 * Created by Lukas on 03.04.2015.
 */
public class MySQLMethods {
/**
    public static void createUserData(ProxiedPlayer pp) {
        String UUID = pp.getUUID().toString();

        if(isInDataBase(pp, "data")) {

            DateFormat dmy = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMANY);
            String lastLoginAsString = dmy.format(System.currentTimeMillis());

            boolean nick = false;
            int logincounter = 0;

            ResultSet rs = MySQL.getResult("SELECT uuid FROM data WHERE uuid = '" + UUID + "'");
            try {
                if (!rs.next()) {
                    MySQL.update("INSERT INTO data VALUES('" + pp.getName() + "', '" + UUID + "', '" + lastLoginAsString + "', '" + logincounter + "' , " + nick + ")");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }*/

    public static void createData() {
        if(dataExists() == false) {
            MySQL.update("INSERT INTO serverdata VALUES('" + BungeeCordMain.standardmotd + "', '" + BungeeCordMain.submotd + "', '" + BungeeCordMain.maintenancesubmotd + "', '" + BungeeCordMain.maintenanceend + "', " + BungeeCordMain.maintenance + ")");
        }
    }

    public static void createTableIfNotExists() {
        MySQL.update("CREATE TABLE IF NOT EXISTS data(name VARCHAR(100), uuid VARCHAR(100), lastlogin VARCHAR(100), logincounter INTEGER, nick BOOLEAN)");

        MySQL.update("CREATE TABLE IF NOT EXISTS rpg(name VARCHAR(100), uuid VARCHAR(100), ep INTEGER, coins INTEGER, campaignprogress INTEGER)");

        MySQL.update("CREATE TABLE IF NOT EXISTS serverdata(motd VARCHAR(100), submotd VARCHAR(100), maintenancesubmotd VARCHAR(100), maintenanceend INTEGER, maintenance BOOLEAN)");
    }

    public static boolean dataExists() {
        boolean dataexists = false;
        int id = 1;

        ResultSet rs = MySQL.getResult("SELECT * FROM serverdata WHERE motd = '" + getMOTD() + "'");

        try {
            if(!rs.next()) {
                dataexists = false;
            } else {
                dataexists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Boolean.valueOf(dataexists);
    }

    public static String getMOTD() {
        String motd = null;
        int id = 1;


        ResultSet ep = MySQL.getResult("SELECT motd FROM serverdata");

        try {
            if(ep.next()){
                motd = ep.getString("motd");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return motd;
    }

    public static String getSubMOTD() {
        String submotd = null;
        int id = 1;


        ResultSet ep = MySQL.getResult("SELECT submotd FROM serverdata");

        try {
            if(ep.next()){
                submotd = ep.getString("submotd");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return submotd;
    }

    public static String getMaintenanceSubMOTD() {
        String maintenancesubmotd = null;

        ResultSet ep = MySQL.getResult("SELECT maintenancesubmotd FROM serverdata");

        try {
            if(ep.next()){
                maintenancesubmotd = ep.getString("maintenancesubmotd");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maintenancesubmotd;
    }

    public static Integer getMaintenanceEnd() {
        int maintenanceend = 0;


        ResultSet ep = MySQL.getResult("SELECT maintenanceend FROM serverdata");

        try {
            if(ep.next()){
                maintenanceend = ep.getInt("maintenanceend");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maintenanceend;
    }

    public static void setSubMOTD(String submotd) {
        MySQL.update("UPDATE serverdata SET submotd = '" + submotd + "'");
    }

    public static void setMOTD(String motd) {
        MySQL.update("UPDATE serverdata SET motd = '" + motd + "'");
    }

    public static void setMaintenanceSubMOTD(String maintenancesubmotd) {
        MySQL.update("UPDATE serverdata SET maintenancesubmotd = '" + maintenancesubmotd + "'");
    }

    public static void setMaintenanceEnd(Integer maintenanceend) {
        MySQL.update("UPDATE serverdata SET maintenanceend = '" + maintenanceend + "'");
    }

    public static void setMaintenance(Boolean maintenance) {
        MySQL.update("UPDATE serverdata SET maintenancesubmotd = '" + maintenance + "'");
    }

    public static boolean getMaintenance() {
        boolean maintenance = false;


        ResultSet ep = MySQL.getResult("SELECT maintenance FROM serverdata");

        try {
            if(ep.next()){
                maintenance = ep.getBoolean("maintenance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maintenance;
    }

    public static boolean isInDataBase(ProxiedPlayer target, String database) {
        String uuid = target.getUniqueId().toString();

        boolean isInDatabase = false;

        ResultSet rs = MySQL.getResult("SELECT uuid FROM " + database + " WHERE uuid = '" + uuid + "'");

        try {
            if(!rs.next()) {
                isInDatabase = true;
            } else {
                isInDatabase = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Boolean.valueOf(isInDatabase).booleanValue();
    }
}
