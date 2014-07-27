package com.silvermineproductions.member_functions;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.silvermineproductions.mysql.mysql;
import com.silvermineproductions.mysql.mysqlcmd;

public class join 
{
	@SuppressWarnings("deprecation")
	public static boolean joinMem(Player p, String[] args)
	{
		if(p.hasPermission("Clans.join"))
		{
			if(args.length == 2)
			{
				if(mysqlcmd.check_Clan_Name(args[1]))
				{
						if(check_appl(p))
						{
							if(mysqlcmd.leader(p.getName()) == 0)
							{
								java.util.Date dNow = new java.util.Date();
								SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' hh:mm");
								
								int clid = mysqlcmd.getClid(args[1]);
								int mid = mysqlcmd.getMid(p.getName());
								int messages = mysqlcmd.getApplications(p) + 1;
								
								mysql.update("INSERT INTO applications (clid, midApplicant, date) VALUES ('" + clid + "', '" + mid + "', '" + ft.format(dNow) + "')");
								mysql.update("UPDATE leader SET messages='" + messages + "' WHERE clid='" + clid + "'");
								p.sendMessage(ChatColor.GREEN + "You succesfully apply for the Clan " + args[1]);
								
								Player leader = Bukkit.getPlayer(mysqlcmd.getMemberName(mysqlcmd.getleader(args[1])));
									p.performCommand("mail send " + leader.getName() + " " 
											+ p.getName() + " want to join your Clan");
								
								return true;
							}
							else
							{
								p.sendMessage(ChatColor.RED + "You are Leader of a Clan");
								return true;
							}
						}
						else
						{
							p.sendMessage(ChatColor.RED + "You already apply for a Clan!");
							return true;
						}
				}
				else
				{
					p.sendMessage(ChatColor.RED + "The Clan " + args[1] + " doesnt exist!");
					return true;
				}
			}
			else
			{
				p.sendMessage("/clans join <name>");
				return true;
			}
			
		}
		else
		{
			p.sendMessage(ChatColor.RED + "You don't have Permissions to perform this command!");
			return true;
		}
	}
	
	private static boolean check_appl(Player p)
	{
		
		try{
        	Statement stmt = mysql.con.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT * FROM applications WHERE midApplicant='" + mysqlcmd.getMid(p.getName()) + "'");
        	rs.last();
        	int count = rs.getRow();
        	
        	if(count == 0){
        		return true;
        	} else {
        		return false;
        	}
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		return false;
	}

}
