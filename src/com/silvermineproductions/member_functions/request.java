package com.silvermineproductions.member_functions;

import java.sql.ResultSet;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.silvermineproductions.mysql.mysql;
import com.silvermineproductions.mysql.mysqlcmd;

public class request 
{
	@SuppressWarnings("deprecation")
	public static boolean invReq(Player p, String[] args)
	{
		if(args.length == 1)
		{
			try{
				
		        Statement stmt = mysql.con.createStatement();
		        ResultSet rs = stmt.executeQuery("SELECT clid FROM invite WHERE mid='" + mysqlcmd.getMid(p.getName()) + "'");
		        p.sendMessage(ChatColor.BLUE + "Requests\n------------------------");
		        rs.last();
		        int count = rs.getRow();
		        
		        if(count != 0)
		        {
			        rs.beforeFirst();
			        while(rs.next())
			        {
			        	p.sendMessage(ChatColor.YELLOW + "- " + mysqlcmd.getclName(rs.getInt("clid")));
			        }
			        return true;
		        }
		        else
		        {
		        	p.sendMessage(ChatColor.RED + "You have no requests");
		        	return true;
		        }
		        	               
	        }catch(Exception e){
	        	System.err.println("Konnte SQL nicht ausführen!");
	            e.printStackTrace();
	        }
		}
		if(args.length == 3)
		{
			if(checkRequest(p, args[1]))
			{
				if(args[2].equalsIgnoreCase("yes"))
				{
					mysql.update("UPDATE member SET clid='" + mysqlcmd.getClid(args[1]) + "'");
					mysql.update("DELETE FROM invite WHERE mid='" + mysqlcmd.getMid(p.getName()) + "' AND clid='" 
							+ mysqlcmd.getClid(args[1]) + "' ");
					p.sendMessage(ChatColor.GREEN + "You succesfully joined the Clan " + args[1]);
					
					Player leader = Bukkit.getPlayer(mysqlcmd.getMemberName(mysqlcmd.getleader(args[1])));
					if(leader != null)
					{
						leader.sendMessage(ChatColor.GREEN + p.getName() + " joined your Clan");
					}
					return true;
				}
				if(args[2].equalsIgnoreCase("no"))
				{
					mysql.update("DELETE FROM invite WHERE mid='" + mysqlcmd.getMid(p.getName()) + "' AND clid='" 
							+ mysqlcmd.getClid(args[1]) + "' ");
					p.sendMessage(ChatColor.GREEN + "You succesfully delete the request");
					
					Player leader = Bukkit.getPlayer(mysqlcmd.getMemberName(mysqlcmd.getleader(args[1])));
					if(leader.isOnline())
					{
						leader.sendMessage(ChatColor.GREEN + p.getName() + " deleted your request");
					}
					else
					{
						p.performCommand("mail send " + mysqlcmd.getMemberName(mysqlcmd.leader(p.getName())) 
								+ " " + p.getName() + " deleted your request");
					}
					return true;
				}
				else
				{
					p.sendMessage("/clan req [clan name] [yes|no]   //Answer a request");
					return true;
				}
			}
			else
			{
				p.sendMessage(ChatColor.RED + "You have no request for the Clan " + args[1]);
				return true;
			}
		}
		else
		{
			p.sendMessage("/clan req [clan name] [yes|no]   //Answer a request");
			return true;
		}
	}
	
	private static boolean checkRequest(Player p, String clName)
	{
		try{
			
	        Statement stmt = mysql.con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT mid FROM invite WHERE mid='" + mysqlcmd.getMid(p.getName()) 
	        		+ "' AND clid='" + mysqlcmd.getClid(clName) + "'");
	        rs.last();
	        int count = rs.getRow();
	        
	        if(count != 0)
	        {
		        return true;
	        }
	        else
	        {
	        	return false;
	        }
	        	               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		return false;
	}
}
