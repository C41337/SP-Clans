package com.silvermineproductions.member_functions;

import java.sql.ResultSet;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.silvermineproductions.mysql.mysql;
import com.silvermineproductions.mysql.mysqlcmd;

public class list 
{
	public static void listClan(Player p)
	{
        try{
        	Statement stmt = mysql.con.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT * FROM clans");
        	
        	p.sendMessage(ChatColor.BLUE + "All Clans:\n------------------");
        	

	        	while (rs.next()) {
	            	p.sendMessage(ChatColor.YELLOW + "- " + rs.getString("clName") + " (" + 
		            		onlineMember(rs.getInt("clid")) + "/" + mysqlcmd.all_Member(rs.getInt("clid")) + ")");
	            }
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
	}
	
	@SuppressWarnings("deprecation")
	public static int onlineMember(int clid)
	{
		int n = 0;
		
		try{
			if(mysqlcmd.all_Member(clid) != 0)
			{
					
	        	Statement stmt = mysql.con.createStatement();
	        	ResultSet rs = stmt.executeQuery("SELECT memName FROM member WHERE clid='" + clid + "'");
	        	
	        	while(rs.next())
	        	{
	        		if(Bukkit.getPlayer(rs.getString("memName")) != null)
	        		{
	        			n = n + 1;
	        		}
	        	}
	        			

			}
			else
			{
				n = 0;
			}
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		
		return n;
	}

}
