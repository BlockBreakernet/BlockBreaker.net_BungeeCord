package me.lusu007.blockbreaker_bungeecord.mysql;

import java.sql.*;

/**
 * Created by Lukas on 26.03.2015.
 */
public class MySQL {

    public static String host;
    public static String port;
    public static String database;
    public static String username;
    public static String password;
    public static Connection con;

    //Connect to MySQL
    public static void connect() {
        if(!isConnected()) {
            try {
                System.out.println(" ");
                System.out.println("Initialisiere MySQL Backend...");
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
                System.out.println("MySQL Verbindung aufgebaut!");
            } catch (SQLException e) {
                System.out.println("Verbindung konnte nicht hergestellt werden!");
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("VendorError: " + e.getErrorCode());
            }
        }
    }

    //Disconnect MySQL
    public static void disconnect() {
        if(isConnected()) {
            try {
                con.close();
                System.out.println("MySQL Verbindung geschlossen!");
            } catch (SQLException e) {
                System.out.println("Verbindung konnte nicht geschlossen werden!");
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("VendorError: " + e.getErrorCode());
            }
        }
    }

    //Returns Connection
    public static boolean isConnected() {
        return (con == null ? false : true);
    }

    //Process a Statement
    public static void update(String qry) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(qry);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Returns a Result
    public static ResultSet getResult(String qry) {
        try {
            PreparedStatement ps = con.prepareStatement(qry);
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
