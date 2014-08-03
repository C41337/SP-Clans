package com.silvermineproductions.admin_leader_functions;

import java.sql.ResultSet;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.silvermineproductions.mysql.mysql;
import com.silvermineproductions.mysql.mysqlcmd;

public class Member_Application 
{
	public static boolean memAppl(Player p, String[] args)
	{
		if(mysqlcmd.check_Leader(p) || mysqlcmd.check_Moderator(p))
		{
			if(args.length == 1)
			{
				show(mysqlcmd.getClidofMember(p.getName()), p);
				return true;
			}
			if(args.length == 3)
			{
				answer(p, args);
				return true;
			}
			else
			{
				p.sendMessage("/clan mR [<name> <yes|no>]");
				return true;
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED + "You don't have Permissions to perform this command!");
			return true;
		}
	}
	
	private static void show(int clid, Player p)
	{
		try{				
	        Statement stmt = mysql.con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT midApplicant, date FROM applications WHERE clid='" + clid + "'");
        	p.sendMessage(ChatColor.BLUE + "Member Requests\n------------------------");
	        rs.last();
	        int count = rs.getRow();
	        if(count != 0)
	        {
	        	rs.beforeFirst();
		        while(rs.next())
		        {
		        	String name = mysqlcmd.getMemberName(rs.getInt("midApplicant"));
		        	String date = rs.getString("date");
		        	
		        	p.sendMessage(ChatColor.YELLOW + "- " + name + " on " + date);
		        }
	        }
	        else
	        {
	        	p.sendMessage(ChatColor.RED + "You have no Member requests");
	        }
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
	}
	@SuppressWarnings("deprecation")
	private static boolean answer(Player p, String[] args)
	{
		if(mysqlcmd.check_Member_Name(args[1]))
		{
			if(check_appl(args[1]))
			{
				if(args[2].equalsIgnoreCase("yes"))
				{
					mysql.update("UPDATE member SET clid='" + mysqlcmd.getClidofMember(p.getName()) + "' WHERE memName='" + args[1] + "'");
					mysql.update("DELETE FROM applications WHERE midApplicant='" + mysqlcmd.getMid(args[1]) + "'");
					int appl = mysqlcmd.getApplications(p) - 1;
					mysql.update("UPDATE leader SET messages='" + appl + "'");
					p.sendMessage(ChatColor.BLUE + args[1] + ChatColor.GREEN + " succesfully joined your Clan");
					
					Player recv = null;
					if((recv = Bukkit.getPlayer(args[1])) != null)
					{
						recv.sendMessage(ChatColor.GREEN + "Your application was accepted!");
						recv.sendMessage(ChatColor.GREEN + "Your are now in the Clan: " + ChatColor.BLUE + mysqlcmd.clName(recv));
					}
					return true;
				}
				if(args[2].equalsIgnoreCase("no"))
				{
					mysql.update("DELETE FROM applications WHERE midApplicant='" + mysqlcmd.getMid(args[1]) + "'");
					p.sendMessage(ChatColor.BLUE + args[1] + ChatColor.RED + " were rejected");
					
					Player recv = Bukkit.getPlayer(args[1]);
					if(recv.isOnline())
					{
						recv.sendMessage(ChatColor.RED + "Your application was not accepted!");
					}
					
					return true;
				}
				else
				{
					p.sendMessage("/clan mR [<name> <yes|no]>");
					
					return true;
				}
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED + "The Member Request " + args[1] + " doesnt exist!!");
			return true;
		}
		return true;
	}
	
	private static boolean check_appl(String name)
	{
		
		try{
        	Statement stmt = mysql.con.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT * FROM applications WHERE midApplicant='" + mysqlcmd.getMid(name) + "'");
        	rs.last();
        	int count = rs.getRow();
        	
        	if(count == 0){
        		return false;
        	} else {
        		return true;
        	}
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		return false;
	}
	
}
