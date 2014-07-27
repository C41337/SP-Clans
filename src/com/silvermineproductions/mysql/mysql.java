package com.silvermineproductions.mysql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;



public class mysql 
{
	public static String host = "";
	public static String user = "";
	public static String pw = "";
	public static String db = "";
	public static String Jdb = "";
	public static int port = 0;
	
	public static Connection con;
	public static Connection Jcon;
	       
	public static void connect()
	{
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db,user,pw);
			Jcon = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + Jdb,user,pw);
			
			ConsoleCommandSender console = Bukkit.getConsoleSender();
			console.sendMessage("[Clans] " + ChatColor.GREEN + "MySQL reconnected ");					
			
		} catch(SQLException e) {
			ConsoleCommandSender console = Bukkit.getConsoleSender();
			console.sendMessage("[Clans] " + ChatColor.RED + "MySQL Connection failed");
		}
		
	}
	public static void reconnect()
	{
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db,user,pw);
			Jcon = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + Jdb,user,pw);					
			
		} catch(SQLException e) {
			ConsoleCommandSender console = Bukkit.getConsoleSender();
			console.sendMessage("[Clans] " + ChatColor.RED + "MySQL Connection failed");
		}
		
	}
	       
	        public static void close()
	        {
	               
	        	if(con != null){	        		
		            try {
		            	con.close();
		            } catch (SQLException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
		            }
	            }
	               
	        }
	       
	        public ResultSet doQuery(String sql) {
	            try {
	                return con.createStatement().executeQuery(sql);
	            } catch(SQLException e) {
	                System.err.println("Konnte das Query ("+sql+") nicht ausführen!");
	            }
	            return null;
	        }
	        
	        public static void update(String query)
	    	{
	        	try {
	        		con.createStatement().executeUpdate(query);
	        	}catch (SQLException e) {
	        		System.err.println(e);
	        	}
	    	}

	       
}
